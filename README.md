Vaadin Charts Export CSV Example
==============

Simple Vaadin application that uses [export csv](https://github.com/highcharts/export-csv) plugin in a chart created using [Vaadin Charts](https://vaadin.com/charts)

Workflow
========

To compile the entire project, run "mvn install".

To run the application, run "mvn jetty:run" and open http://localhost:8080/ .

Debugging client side code
  - run "mvn vaadin:run-codeserver" on a separate console while the application is running
  - activate Super Dev Mode in the debug window of the application

To produce a deployable production mode WAR:
- change productionMode to true in the servlet class configuration (nested in the UI class)
- run "mvn clean package"
- test the war file with "mvn jetty:run-war"

Step by Step
========
1. Create a Vaadin project using vaadin-archetype-application, add vaadin-charts dependency
2. Add `HighchartsPluginResources` class in client package and create a `TextResource` for the plugin js file. Add plugin js file in same folder but under src/main/resources.
3. Create `HighchartsPluginScriptLoader` class extending `HighchartsScriptLoader`. Override `injectResources` so that both original and new plugin resources are injected.
4. In project `widgetset.gwt.xml` file replace the original script loader with extended one:
```
    <replace-with class="uy.com.chartexport.client.HighchartsPluginScriptLoader">
        <when-type-is class="com.vaadin.addon.charts.client.HighchartsScriptLoader"/>
    </replace-with>
```
Remember to enable chart exporting.

