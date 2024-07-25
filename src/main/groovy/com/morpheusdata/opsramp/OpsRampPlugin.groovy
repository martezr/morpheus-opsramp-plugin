package com.morpheusdata.opsramp

import com.morpheusdata.core.Plugin
import com.morpheusdata.model.Permission
import com.morpheusdata.views.HandlebarsRenderer
import com.morpheusdata.views.ViewModel
import com.morpheusdata.web.Dispatcher
import com.morpheusdata.model.OptionType

class OpsRampPlugin extends Plugin {

	@Override
	String getCode() {
		return 'opsramp-plugin'
	}

	@Override
	void initialize() {
		OpsRampTabProvider opsRampTabProvider = new OpsRampTabProvider(this, morpheus)
		this.pluginProviders.put(opsRampTabProvider.code, opsRampTabProvider)
		this.setName("OpsRamp Plugin")
		this.setDescription("OpsRamp integration")
		this.setAuthor("Martez Reed")
		this.setSourceCodeLocationUrl("https://github.com/martezr/morpheus-opsramp-plugin")
		this.setIssueTrackerUrl("https://github.com/martezr/morpheus-opsramp-plugin/issues")
		this.setPermissions([Permission.build('OpsRamp Instance Tab','opsramp-instance-tab', [Permission.AccessType.none, Permission.AccessType.full])])
		// Plugin settings the are used to configure the behavior of the plugin
		// https://developer.morpheusdata.com/api/com/morpheusdata/model/OptionType.html
		this.settings << new OptionType(
			name: 'API Endpoint',
			code: 'opsramp-plugin-api-endpoint',
			fieldName: 'opsRampApiEndpoint',
			displayOrder: 0,
			defaultValue: 'https://api.opsramp.com',
			fieldLabel: 'API Endpoint',
			helpText: 'The OpsRamp API Endpoint',
			required: true,
			inputType: OptionType.InputType.TEXT
		)

		this.settings << new OptionType(
			name: 'Tenant ID',
			code: 'opsramp-plugin-tenant-id',
			fieldName: 'opsRampTenantId',
			displayOrder: 1,
			fieldLabel: 'Tenant ID',
			helpText: 'The ID of the OpsRamp tenant',
			required: true,
			inputType: OptionType.InputType.TEXT
		)

		this.settings << new OptionType(
			name: 'Key',
			code: 'opsramp-plugin-key',
			fieldName: 'opsRampKey',
			displayOrder: 2,
			fieldLabel: 'Key',
			helpText: 'The OpsRamp key',
			required: true,
			inputType: OptionType.InputType.PASSWORD
		)

		this.settings << new OptionType(
			name: 'Secret',
			code: 'opsramp-plugin-secret',
			fieldName: 'opsRampSecret',
			displayOrder: 3,
			fieldLabel: 'Secret',
			helpText: 'The OpsRamp secret',
			required: true,
			inputType: OptionType.InputType.PASSWORD
		)

		this.settings << new OptionType(
			name: 'Visible Environments',
			code: 'opsramp-plugin-environments-field',
			fieldName: 'environmentVisibilityField',
			defaultValue: 'any',
			displayOrder: 4,
			fieldLabel: 'Environments',
			fieldGroup: 'Visibility Settings',
			helpText: 'List of the names of the Morpheus environments the tab is visible (i.e. - production,qa,dev)',
			required: false,
			inputType: OptionType.InputType.TEXT
		)

		this.settings << new OptionType(
			name: 'Visible Groups',
			code: 'opsramp-plugin-groups-field',
			fieldName: 'groupVisibilityField',
			defaultValue: 'any',
			displayOrder: 5,
			fieldLabel: 'Groups',
			fieldGroup: 'Visibility Settings',
			helpText: 'List of the names of the Morpheus groups the tab is visible (i.e. - devs,admins,security)',
			required: false,
			inputType: OptionType.InputType.TEXT
		)

		this.settings << new OptionType(
			name: 'Visible Tags',
			code: 'opsramp-plugin-tags-field',
			fieldName: 'tagVisibilityField',
			defaultValue: 'opsramp',
			displayOrder: 6,
			fieldLabel: 'Tags',
			fieldGroup: 'Visibility Settings',
			helpText: 'List of the names of the Morpheus tags the tab is visible (i.e. - datadog,monitoring,observability)',
			required: false,
			inputType: OptionType.InputType.TEXT
		)
	}

	@Override
	void onDestroy() {}
}