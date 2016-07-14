package com.gtsoft.meddyl.customer.model.response;

import com.gtsoft.meddyl.customer.model.object.System_Successful;


public class JSONSuccessfulResponse extends JSONResponse
{
    private System_Successful system_successful_obj;

    private Object data_obj;

    public System_Successful getSystemSuccessful() {
        return this.system_successful_obj;
    }

    public void setSystemSuccessful(System_Successful system_successful_obj) {
        this.system_successful_obj = system_successful_obj;
    }


    public Object getDataObj() {
        return this.data_obj;
    }

    public void setDataObj(Object data_obj) {
        this.data_obj = data_obj;
    }


}

