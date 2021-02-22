package com.inteligo.application.views.findmyatm;

import com.inteligo.application.data.entity.SamplePerson;
import com.inteligo.application.data.service.SamplePersonService;
import com.inteligo.application.views.main.MainView;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.customfield.CustomField;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.EmailField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouteAlias;

@Route(value = "home", layout = MainView.class)
@PageTitle("Find my atm")
@CssImport("./views/findmyatm/findmyatm-view.css")
@RouteAlias(value = "", layout = MainView.class)
public class FindmyatmView extends Div {

    private ComboBox<String> phone = new ComboBox("Select Currency");
    private EmailField email = new EmailField("1 USD equals");


    private Button cancel = new Button("Cancel");
    private Button save = new Button("Save");

    private Binder<SamplePerson> binder = new Binder(SamplePerson.class);

    public FindmyatmView(SamplePersonService personService) {
        addClassName("findmyatm-view");

        add(createTitle());
        add(createFormLayout());
        add(createButtonLayout());

        binder.bindInstanceFields(this);
        clearForm();

        cancel.addClickListener(e -> clearForm());
        save.addClickListener(e -> {
            personService.update(binder.getBean());
            Notification.show(binder.getBean().getClass().getSimpleName() + " details stored.");
            clearForm();
        });
    }

    private void clearForm() {
        binder.setBean(new SamplePerson());
    }

    private Component createTitle() {
        return new H3("Personal information");
    }

    private Component createFormLayout() {
        FormLayout formLayout = new FormLayout();

        phone.setWidth("120px");
        phone.setPlaceholder("Select Currency");
        phone.setPattern("\\+\\d*");
        phone.setPreventInvalidInput(true);
        phone.setItems("+354", "+91", "+62", "+98", "+964", "+353", "+44", "+972", "+39", "+225");
        phone.addCustomValueSetListener(e -> phone.setValue(e.getDetail()));

        email.setErrorMessage("Please enter a valid email address");
        formLayout.add(phone, email);
        return formLayout;
    }

    private Component createButtonLayout() {
        HorizontalLayout buttonLayout = new HorizontalLayout();
        buttonLayout.addClassName("button-layout");
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        buttonLayout.add(save);
        buttonLayout.add(cancel);
        return buttonLayout;
    }

}
