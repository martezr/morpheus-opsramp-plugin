# Morpheus OpsRamp Integration

The OpsRamp integration plugin integrates with the OpsRamp monitoring solution to bring contextual workload data from OpsRamp into the Morpheus UI. The plugin adds a new OpsRamp tab to Morpheus instances that includes monitoring details pulled from OpsRamp.

## Requirements

The following requirements must be met prior to using the OpsRamp plugin:

* An OpsRamp account (https://www.opsramp.com/).
* An API key and Secret key that will be used by the plugin to perform API calls to the OpsRamp API (https://develop.opsramp.com/v2).
* A compatible Morpheus platform version. The Morpheus platform version must be equal to or higher than the version specified on the plugin.

## Installation

Once the plugin file is downloaded, browse to the Administration -> Integrations -> Plugins section in the Morpheus UI. Click the Upload File button to select your plugin and upload it. The plugin should now be loaded into the environment for use.

## Configuration

With the plugin installed, there are a few configuration steps required before you are able to use the plugin. 

### Plugin Settings

The plugin supports dynamic configuration via the plugin settings which are accessed by clicking on the pencil next to the plugin.

The following settings are available for the OpsRamp plugin:

|Setting|Description|Required|Default|
|---|---|---|---|
|API Key |The generated OpsRamp API key used to authenticate to the OpsRamp REST API |  Yes |n/a |
| Secret Key | The generated OpsRamp Secret key used to authenticate to the OpsRamp REST API | Yes |n/a |
| Environments | This toggles the visibility of the tab based upon the Morpheus environment the instance is in. Multiple environments are supported by adding multiple comma separated environments in the text box.| No| any|
| Groups | This toggles the visibility of the tab based upon the Morpheus group the instance is in. Multiple groups are supported by adding multiple comma separated groups in the text box.| No|any |
| Tags | This toggles the visibility of the tab based upon the tag(s) assigned to the instance. The tag key is what is used for the evaluation. Multiple tags are supported by adding multiple comma separated tags in the text box.|No| opsramp |

### Permissions

The OpsRamp plugin adds a new RBAC permission to the Morpheus platform. 

**Permission Name**

```
OpsRamp Instance Tab	
```
The permissions supports FULL and NONE access types which determines whether the user sees the instance tab or not.

## Usage

Once the plugin has been installed and configured, any instance in the appropriate group, environment and with the required tags will display the OpsRamp tab on the instance page.