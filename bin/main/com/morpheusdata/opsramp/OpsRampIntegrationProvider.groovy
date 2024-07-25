package com.morpheusdata.opsramp

import com.morpheusdata.core.providers.AbstractGenericIntegrationProvider
import com.morpheusdata.core.MorpheusContext
import com.morpheusdata.core.Plugin
import com.morpheusdata.views.HTMLResponse
import com.morpheusdata.model.OptionType
import com.morpheusdata.views.ViewModel
import com.morpheusdata.model.AccountIntegration
import groovy.util.logging.Slf4j
import com.morpheusdata.model.*

@Slf4j
class OpsRampIntegrationProvider extends AbstractGenericIntegrationProvider {
	Plugin plugin
	MorpheusContext morpheus

	String code = 'opsramp'
	String name = 'OpsRamp'

	OpsRampIntegrationProvider(Plugin plugin, MorpheusContext context) {
		this.plugin = plugin
		this.morpheus = context
	}

	@Override
	String getCategory() {
		return 'monitoring'
	}

	@Override
	Icon getIcon() {
		return new Icon()
	}

	@Override
	void refresh(AccountIntegration accountIntegration) {
		log.debug "daily refresh run for ${accountIntegration}"
	}

	@Override
	List<OptionType> getOptionTypes() {
		OptionType apiUrl = new OptionType(
			name: 'API Endpoint',
			code: 'opsramp-plugin-apiEndpoint',
			fieldName: 'apiEndpoint',
			defaultValue: 'any',
			displayOrder: 0,
			fieldLabel: 'API Endpoint',
			helpText: 'List of the names of the Morpheus environments the tab is visible (i.e. - production,qa,dev)',
			required: true,
			inputType: OptionType.InputType.TEXT
		)
		return [apiUrl]
	}

	@Override
	HTMLResponse renderTemplate(AccountIntegration integration) {
		println "Integration Details: ${integration.account}"
		println "Integration Details: ${integration.config}"
		println "Integration Details: ${integration.id}"
		//println "Integration Details: ${integration.Status}"
		println "Integration Details: ${integration.objectRefs}"
		println "Integration Details: ${integration.serviceConfig}"

		// Instantiate an object for storing data
		// passed to the html template
		ViewModel<AccountIntegration> model = new ViewModel<>()
		
        model.object = integration
		getRenderer().renderTemplate("hbs/integration", model)
		//getRenderer().renderTemplate("hbs/instanceNotFoundTab", model)
	}
}