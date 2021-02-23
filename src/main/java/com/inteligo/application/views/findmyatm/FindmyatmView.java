package com.inteligo.application.views.findmyatm;

import com.flowingcode.vaadin.addons.googlemaps.GoogleMap;
import com.flowingcode.vaadin.addons.googlemaps.GoogleMap.MapType;
import com.flowingcode.vaadin.addons.googlemaps.GoogleMapMarker;
import com.flowingcode.vaadin.addons.googlemaps.Icon;
import com.flowingcode.vaadin.addons.googlemaps.LatLon;
import com.inteligo.application.data.entity.CurrencyItem;
import com.inteligo.application.data.entity.ExchangeRate;
import com.inteligo.application.data.service.AtmService;
import com.inteligo.application.data.service.ExchangeRateService;
import com.inteligo.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

@Slf4j
@Route(value = "home", layout = MainView.class)
@PageTitle("Find my atm")
@CssImport("./views/findmyatm/findmyatm-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class FindmyatmView extends VerticalLayout {

  private ComboBox<CurrencyItem> currency = new ComboBox("Select Currency");
  private TextField rate = new TextField("1 USD equals");
  private GoogleMap gmaps;


  private Button findAtms = new Button("Find ATMs");
  private Button getRates = new Button("Exchange Rate");

  private Binder<ExchangeRate> binder = new Binder(ExchangeRate.class);

  public FindmyatmView(ExchangeRateService exchangeRateService, AtmService atmService) {
    addClassName("findmyatm-view");
    this.setSizeFull();

    gmaps = new GoogleMap("AIzaSyD7nUnzXhhUachR0UoJMqnVuIAlPGe4D18", null, null);

    add(createTitle());
    add(createFormLayout());
    add(createButtonLayout());


    binder.bindInstanceFields(this);
    clearForm();

    findAtms.addClickListener(e -> {
      gmaps.setMapType(MapType.TERRAIN);
      gmaps.setCenter(new LatLon(51.877243, 5.837550));
      gmaps.setSizeFull();
      GoogleMapMarker flowingmarker = gmaps.addMarker("Center", new LatLon(51.877243, 5.837550), true,
          "https://www.flowingcode.com/wp-content/uploads/2020/06/FCMarker.png");
      gmaps.addMarker(flowingmarker);

      atmService.getAtmsLocation().forEach(atm -> {

        GoogleMapMarker marker = gmaps.addMarker("Center", new LatLon(atm.getLatitude(), atm.getLongitude()), true,
            "https://i.ibb.co/SJSWxNR/atm.png");
        gmaps.addMarker(marker);
      });

      add(gmaps);
    });
    getRates.addClickListener(e -> {

      ExchangeRate exr = exchangeRateService.getExchangeRate(currency.getValue().getCode());
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

    currency.setItems(fillCurrency());
    currency.setItemLabelGenerator(CurrencyItem::getName);

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

  private List<CurrencyItem> fillCurrency() {
    List<CurrencyItem> currencyItems = new ArrayList<>();
    currencyItems.add(CurrencyItem.builder().code("EUR").name("Euro").build());
    currencyItems.add(CurrencyItem.builder().code("GBP").name("British Pound Sterling").build());
    currencyItems.add(CurrencyItem.builder().code("CAD").name("Canadian Dollar").build());
    currencyItems.add(CurrencyItem.builder().code("PLN").name("Polish Zloty").build());
    currencyItems.add(CurrencyItem.builder().code("PEN").name("Peruvian Nuevo Sol").build());
    currencyItems.add(CurrencyItem.builder().code("MXN").name("Mexican Peso").build());


    return currencyItems;
  }

}
