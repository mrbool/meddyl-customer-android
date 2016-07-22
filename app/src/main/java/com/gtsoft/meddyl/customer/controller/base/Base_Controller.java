package com.gtsoft.meddyl.customer.controller.base;


import com.gtsoft.meddyl.customer.BuildConfig;
import com.gtsoft.meddyl.customer.model.object.Application_Type;
import com.gtsoft.meddyl.customer.model.object.Login_Log;
import com.gtsoft.meddyl.customer.system.gtsoft.Utils;

public class Base_Controller
{
    protected boolean successful;
    protected String message;
    protected String customer_service = "https://api.meddyl.com/1.10/services/CustomerService.svc/";
    protected Application_Type application_type_obj;
    protected Login_Log login_log_obj;

    /* constructor */
    public Base_Controller()
    {
        String version = BuildConfig.VERSION_NAME;
        String ip_address = Utils.getIPAddress(true);

        application_type_obj = new Application_Type();
        application_type_obj.setApplicationTypeId(3);
        application_type_obj.setVersion(version);

        login_log_obj = new Login_Log();
        login_log_obj.setIpAddress(ip_address);
        login_log_obj.setApplicationTypeObj(application_type_obj);
    }


    public boolean getSuccessful()
    {
        return this.successful;
    }

    public void setSuccessful(boolean successful)
    {
        this.successful = successful;
    }

    public String getMessage()
    {
        return this.message;
    }

    public void setMessage(String message)
    {
        this.message = message;
    }

}
