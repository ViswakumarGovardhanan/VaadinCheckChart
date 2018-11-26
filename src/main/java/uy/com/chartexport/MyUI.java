package uy.com.chartexport;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Set;

import javax.servlet.annotation.WebServlet;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.vaadin.addon.charts.Chart;
import com.vaadin.addon.charts.model.AxisTitle;
import com.vaadin.addon.charts.model.ChartType;
import com.vaadin.addon.charts.model.Configuration;
import com.vaadin.addon.charts.model.DataLabels;
import com.vaadin.addon.charts.model.DataSeries;
import com.vaadin.addon.charts.model.DataSeriesItem;
import com.vaadin.addon.charts.model.ListSeries;
import com.vaadin.addon.charts.model.PlotOptionsFunnel;
import com.vaadin.addon.charts.model.PlotOptionsLine;
import com.vaadin.addon.charts.model.PlotOptionsPie;
import com.vaadin.addon.charts.model.WaterFallSum;
import com.vaadin.addon.charts.model.XAxis;
import com.vaadin.addon.charts.model.YAxis;
import com.vaadin.addon.charts.model.style.Color;
import com.vaadin.addon.charts.model.style.SolidColor;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.annotations.Widgetset;
import com.vaadin.server.VaadinRequest;
import com.vaadin.server.VaadinServlet;
import com.vaadin.ui.Button;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Button.ClickEvent;

/**
 * This UI is the application entry point. A UI may either represent a browser window 
 * (or tab) or some part of a html page where a Vaadin application is embedded.
 * <p>
 * The UI is initialized using {@link #init(VaadinRequest)}. This method is intended to be 
 * overridden to add component to the user interface and initialize non-component functionality.
 */
@Theme("mytheme")
@Widgetset("uy.com.chartexport.MyAppWidgetset")
public class MyUI extends UI {
	
	  private int clickCounter = 0;
	    private Label clickCounterLabel;

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final VerticalLayout layout = new VerticalLayout();

       // layout.addComponent(getChart());
        layout .setMargin(true);
        
        TextField meetingpurposefield = new TextField("first value");
        
        TextField meetingpurposefieldtwo = new TextField("second value");
        
        Button button = new Button("Click Me");
        button.addClickListener(new Button.ClickListener() {

            @Override
            public void buttonClick(ClickEvent event) {
                clickCounter++;
                clickCounterLabel.setValue("Clicks: " + clickCounter);
                Notification.show("Thank you for clicking.");
                String auth_user="viswa", auth_pwd = "viswa@123";
                
                
               String encoded_pwd = "";
               try {
				encoded_pwd = URLEncoder.encode(auth_pwd, "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

                String client_url =
                		
                		"mongodb://" + auth_user + ":" + encoded_pwd 
                		+ "@" + "ds031902.mlab.com" + ":" + 31902 + ""
                				+ "/" + "checkvaadintest";
               

                
              //  mongodb://<dbuser>:<dbpassword>@ds031902.mlab.com:31902/checkvaadintest
                
                MongoClientURI uri = new MongoClientURI(client_url);
               
               MongoClient mongoClient = new MongoClient(uri);
              
              DB viswadbconnection = mongoClient.getDB("checkvaadintest");
              
              
              DBCollection collection = viswadbconnection.getCollection("checkone");
              /**** Insert ****/
              // create a document to store key and value
              BasicDBObject document = new BasicDBObject();
              document.put("firstName", meetingpurposefield.getValue());
              document.put("lastName", meetingpurposefieldtwo.getValue());
              collection.insert(document);
              System.out.println(collection.getCount() +"longvalue");
              Set<String> collectionname = viswadbconnection.getCollectionNames();
              
              System.out.println(collectionname +"collectionnamecollectionname");
           
                /**** Get database ****/
                // if database doesn't exists, MongoDB will create it for you
              // DB db = mongoClient.getDB("testdb");
              //  mongoClient.getDatabaseNames().forEach(System.out::println);
                /**** Get collection / table from 'testdb' ****/
                // if collection doesn't exists, MongoDB will create it for you
             //  DBCollection collection = db.getCollection("users");
                /**** Insert ****/
                // create a document to store key and value
               // BasicDBObject document = new BasicDBObject();
                document.put("firstName", "Dharam");
                document.put("lastName", "Rajput");
               // collection.insert(document);
                
                
                ///////////////
                
               
            }
        });
        layout.addComponent(meetingpurposefield);
        layout.addComponent(meetingpurposefieldtwo);
	        layout.addComponent(button);
	        layout.addComponent(clickCounterLabel = new Label("Clicks: 0"));
	        
	        
	        
	      
		                
		   
		   HorizontalLayout line01 = new HorizontalLayout();
		   HorizontalLayout line02 = new HorizontalLayout();
		   
		   line01.addComponent(getLineChart());
		   line01.addComponent(getWaterfallChart());
		   line02.addComponent(getPieChart());
		   line02.addComponent(getFunnelChart());
		   layout.addComponent(line01);
		   layout.addComponent(line02);
		   
		  

        setContent(layout);
    }
		   

    private Chart getLineChart()
    {
    	   Chart chart = new Chart();
    	   chart.setWidth("300px");
    	   chart.setHeight("300px");
    	   Configuration conf = chart.getConfiguration();
    	   conf.getChart().setType(ChartType.LINE);
    	   conf.setTitle("Line Chart");

    	   DataSeries serie1 = new DataSeries("SERIE 1");
    	   serie1.add(new DataSeriesItem(0,0));
    	   serie1.add(new DataSeriesItem(1,1));
    	   serie1.add(new DataSeriesItem(2,2));
    	   serie1.add(new DataSeriesItem(3,3));
    	   serie1.add(new DataSeriesItem(4,4));
    	   conf.addSeries(serie1);
    	   PlotOptionsLine serie1Opts = new PlotOptionsLine();
    	   serie1Opts.setColor(SolidColor.BLUE);
    	   serie1.setPlotOptions(serie1Opts);

    	   DataSeries serie2 = new DataSeries("SERIE 2");
    	   serie2.add(new DataSeriesItem(0,2));
    	   serie2.add(new DataSeriesItem(1,2));
    	   serie2.add(new DataSeriesItem(2,3));
    	   serie2.add(new DataSeriesItem(3,4));
    	   serie2.add(new DataSeriesItem(4,5));
    	   conf.addSeries(serie2);
    	   PlotOptionsLine serie2Opts = new PlotOptionsLine();
    	   serie2Opts.setColor(SolidColor.RED);
    	   serie2.setPlotOptions(serie2Opts);
    	   
    	   return chart;
    }

    private Chart getWaterfallChart()
    {
    	Chart chart = new Chart(ChartType.WATERFALL);
    	chart.setWidth("300px");
    	chart.setHeight("300px");

    	Configuration conf = chart.getConfiguration();
    	conf.setTitle("Waterfall Chart");
    	conf.getLegend().setEnabled(false);

    	XAxis xaxis = new XAxis();
    	xaxis.setCategories("Initial value", "+90", "-70" , "Total");
    	conf.addxAxis(xaxis);

    	YAxis yaxis = new YAxis();
    	yaxis.setTitle("Accumulator");
    	conf.addyAxis(yaxis);
    	final Color negative = SolidColor.RED;
    	final Color positive = SolidColor.GREEN;

    	DataSeries series = new DataSeries();

    	DataSeriesItem start = new DataSeriesItem("Start", 100);
    	start.setColor(positive);
    	series.add(start);
    	
    	DataSeriesItem a90 = new DataSeriesItem("+90", +90);
    	a90.setColor(positive);
    	series.add(a90);
    	
    	DataSeriesItem m70 = new DataSeriesItem("-70", -70);
    	m70.setColor(negative);
    	series.add(m70);

    	WaterFallSum end = new WaterFallSum("Total");
    	end.setColor(positive);
    	end.setIntermediate(false);
    	series.add(end);

    	conf.addSeries(series);		
    	return chart;
    }

    private Chart getPieChart()
    {
    	Chart chart = new Chart(ChartType.PIE);
    	chart.setWidth("300px");
    	chart.setHeight("300px");		
    	Configuration conf = chart.getConfiguration();
    	conf.setTitle("Pie Chart");
    	PlotOptionsPie options = new PlotOptionsPie();
    	options.setInnerSize("50px");
    	options.setSize("75%");
    	options.setCenter("50%", "50%");
    	conf.setPlotOptions(options);
    	DataSeries series = new DataSeries();
    	series.add(new DataSeriesItem("35%", 35));
    	series.add(new DataSeriesItem("40%", 40));
    	DataSeriesItem  s25 = new DataSeriesItem("25%", 25);
    	s25.setSliced(true);
    	series.add(s25);
    	conf.addSeries(series);		
    	
    	return chart;
    }


    private Chart getFunnelChart()
    {
    	Chart chart = new Chart(ChartType.FUNNEL);
    	chart.setWidth("300px");
    	chart.setHeight("300px");		

    	Configuration conf = chart.getConfiguration();
    	conf.setTitle("Funnel Chart");
    	conf.getLegend().setEnabled(false);

    	PlotOptionsFunnel options = new PlotOptionsFunnel();
    	options.setNeckHeight("50%");
    	options.setNeckWidth("50%");

    	conf.setPlotOptions(options);

    	DataSeries series = new DataSeries();
    	series.add(new DataSeriesItem("100", 100));
    	series.add(new DataSeriesItem("50", 50));
    	series.add(new DataSeriesItem("30", 30));
    	series.add(new DataSeriesItem("20", 20));
    	conf.addSeries(series);
    	return chart;
    }
    private Chart getChart() {
        Chart chart = new Chart();
        chart.setHeight("450px");
        chart.setWidth("100%");

        Configuration configuration = chart.getConfiguration();
        configuration.getChart().setType(ChartType.SPLINE);
        configuration.setExporting(true);

        configuration.getTitle().setText("Monthly Average Temperature");
        configuration.getSubTitle().setText("Source: WorldClimate.com");

        configuration.getxAxis().setCategories("Jan", "Feb", "Mar", "Apr",
                "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec");

        YAxis yAxis = configuration.getyAxis();
        yAxis.setTitle(new AxisTitle("Temperature (°C)"));

        configuration
                .getTooltip()
                .setFormatter(
                        "'<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y +'°C'");

        PlotOptionsLine plotOptions = new PlotOptionsLine();
        plotOptions.setDataLabels(new DataLabels(true));
        configuration.setPlotOptions(plotOptions);

        ListSeries ls = new ListSeries();
        ls.setName("Tokyo");
        ls.setData(7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3,
                13.9, 9.6);
        configuration.addSeries(ls);

        ls = new ListSeries();
        ls.setName("London");
        ls.setData(3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6,
                4.8);
        configuration.addSeries(ls);

        return chart;
    }

    @WebServlet(urlPatterns = "/*", name = "MyUIServlet", asyncSupported = true)
    @VaadinServletConfiguration(ui = MyUI.class, productionMode = false)
    public static class MyUIServlet extends VaadinServlet {
    }
}
