package com.inteligo.application.views.findmyatm;

import com.flowingcode.vaadin.addons.googlemaps.GoogleMap;
import com.flowingcode.vaadin.addons.googlemaps.GoogleMap.MapType;
import com.flowingcode.vaadin.addons.googlemaps.GoogleMapPoint;
import com.flowingcode.vaadin.addons.googlemaps.GoogleMapPolygon;
import com.flowingcode.vaadin.addons.googlemaps.LatLon;
import com.inteligo.application.data.entity.ExchangeRate;
import com.inteligo.application.data.service.ExchangeRateService;
import com.inteligo.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

@Slf4j
@Route(value = "home", layout = MainView.class)
@PageTitle("Find my atm")
@CssImport("./views/findmyatm/findmyatm-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class FindmyatmView extends Div {

    private ComboBox<String> currency = new ComboBox("Select Currency");
    private TextField rate = new TextField("1 USD equals");
    private GoogleMap gmaps = new GoogleMap("AIzaSyD7nUnzXhhUachR0UoJMqnVuIAlPGe4D18", null, null);


    private Button findAtms = new Button("Find ATMs");
    private Button getRates = new Button("Exchange Rate");

    private Binder<ExchangeRate> binder = new Binder(ExchangeRate.class);

    public FindmyatmView(ExchangeRateService exchangeRateService) {
        addClassName("findmyatm-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());
        //add(createMapLayout());

        binder.bindInstanceFields(this);
        clearForm();

        findAtms.addClickListener(e -> {
            //add(createMapLayout());
        });
        getRates.addClickListener(e -> {

            ExchangeRate exr = exchangeRateService.getExchangeRate(currency.getValue());
            log.info("EXR = {}", exr);
            rate.setValue(exr.getRate());
        });
    }

    private void clearForm() {
        binder.setBean(new ExchangeRate());
    }

    private Component createTitle() {
        return new H3("Personal information");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();

        currency.setWidth("120px");
        currency.setPlaceholder("Select Currency");
        currency.setPreventInvalidInput(true);
        currency.setItems("EUR", "GBP","CAD","PLN");
        currency.addCustomValueSetListener(e -> currency.setValue(e.getDetail()));

        formLayout.add(currency, rate);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");

        getRates.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        findAtms.addThemeVariants(ButtonVariant.LUMO_CONTRAST);

        buttonLayout.add(getRates);
        buttonLayout.add(findAtms);
        return buttonLayout;
    }

    private Component createMapLayout() {

        VerticalLayout mapLayout = new VerticalLayout();
        mapLayout.setSizeFull();

        gmaps.setMapType(MapType.SATELLITE);
        gmaps.setMapType(MapType.SATELLITE);
        gmaps.setSizeFull();
        gmaps.setCenter(new LatLon(0,0));
        gmaps.addMarker("Center", new LatLon(0,0), true, "");
        GoogleMapPolygon gmp = gmaps.addPolygon(Arrays.asList(new GoogleMapPoint(gmaps.getCenter()),
            new GoogleMapPoint(gmaps.getCenter().getLat(),gmaps.getCenter().getLon()+1),
            new GoogleMapPoint(gmaps.getCenter().getLat()+1,gmaps.getCenter().getLon())));

        mapLayout.add(gmaps);
        return mapLayout;


    }
}
