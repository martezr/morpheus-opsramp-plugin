package com.morpheusdata.opsramp

import com.morpheusdata.core.AbstractInstanceTabProvider
import com.morpheusdata.core.MorpheusContext
import com.morpheusdata.core.Plugin
import com.morpheusdata.core.cypher.CypherAccess
import com.morpheusdata.model.Account
import com.morpheusdata.model.Instance
import com.morpheusdata.model.TaskConfig
import com.morpheusdata.model.ContentSecurityPolicy
import com.morpheusdata.model.User
import com.morpheusdata.views.HTMLResponse
import com.morpheusdata.views.ViewModel
import com.morpheusdata.core.util.RestApiUtil
import groovy.json.JsonOutput
import groovy.json.JsonSlurper
import groovy.util.logging.Slf4j
import java.time.LocalDateTime
import groovy.time.TimeCategory
import java.time.LocalDateTime

@Slf4j
class OpsRampTabProvider extends AbstractInstanceTabProvider {
	Plugin plugin
	MorpheusContext morpheus
	RestApiUtil opsRampAPI

	String code = 'opsramp'
	String name = 'OpsRamp'

	OpsRampTabProvider(Plugin plugin, MorpheusContext context) {
		this.plugin = plugin
		this.morpheus = context
		this.opsRampAPI = new RestApiUtil()
	}

	OpsRampTabProvider(Plugin plugin, MorpheusContext morpheusContext, RestApiUtil api, Account account) {
		this.morpheusContext = morpheusContext
		this.plugin = plugin
		this.opsRampAPI = api
	}
	
	@Override
	HTMLResponse renderTemplate(Instance instance) {
		// Instantiate an object for storing data
		// passed to the html template
		ViewModel<Instance> model = new ViewModel<>()
		
		// Retrieve additional details about the instance
        // https://developer.morpheusdata.com/api/com/morpheusdata/model/TaskConfig.InstanceConfig.html
		TaskConfig instanceDetails = morpheus.buildInstanceConfig(instance, [:], null, [], [:]).blockingGet()

		// Define an object for storing the data retrieved
		// from the OpsRamp REST API
		def HashMap<String, String> opsRampPayload = new HashMap<String, String>();

		// Set the webnonce to allow JavaScript and CSS
		def webnonce = morpheus.getWebRequest().getNonceToken()
		opsRampPayload.put("webnonce",webnonce)
        model.object = opsRampPayload

		// Support custom view templates
		switch(instance.provisionType) {
			case "opsramp":
				return getRenderer().renderTemplate("hbs/defaultInstanceTab", model)
			case "demo":
				break; 
			default: 
				return getRenderer().renderTemplate("hbs/defaultInstanceTab", model)
		}
	}


	// This method contains the logic for when the tab
	// should be displayed in the UI
	@Override
	Boolean show(Instance instance, User user, Account account) {
		// Set the tab to not be shown be default
		def show = false
		// Only show the opsramp tab when the instance type code is opsramp
		if (instance.instanceTypeCode == "opsramp"){
			return true
		}
		return show
	}

	@Override
	ContentSecurityPolicy getContentSecurityPolicy() {
		def csp = new ContentSecurityPolicy()
		csp
	}
}