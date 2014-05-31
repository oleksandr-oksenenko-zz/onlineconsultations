package net.onlineconsultations.web.admin.form;

import net.onlineconsultations.domain.Administrator;

public class AdministratorForm extends UserForm {

    public static AdministratorForm of(Administrator administrator) {
        return new AdministratorForm(administrator.getUsername());
    }

    public AdministratorForm() { }

    public AdministratorForm(String username) {
        super(username);
    }

}
