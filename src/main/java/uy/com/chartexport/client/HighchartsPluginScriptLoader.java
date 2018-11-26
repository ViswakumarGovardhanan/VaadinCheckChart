package uy.com.chartexport.client;

import com.vaadin.addon.charts.client.HighchartsScriptLoader;

public class HighchartsPluginScriptLoader extends HighchartsScriptLoader {

    @Override
    protected void injectResources() {
        super.injectResources();
        inject(HighchartsPluginResources.INSTANCE.exportCSV().getText());
    }
}