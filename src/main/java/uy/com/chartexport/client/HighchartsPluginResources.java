package uy.com.chartexport.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.TextResource;

public interface HighchartsPluginResources extends ClientBundle {

    public static final HighchartsPluginResources INSTANCE = GWT
            .create(HighchartsPluginResources.class);

    @Source("export-csv.js")
    TextResource exportCSV();

}