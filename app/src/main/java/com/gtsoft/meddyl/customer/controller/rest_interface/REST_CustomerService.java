package com.gtsoft.meddyl.customer.controller.rest_interface;

import com.google.gson.*;
import com.gtsoft.meddyl.customer.model.object.*;
import com.gtsoft.meddyl.customer.model.response.*;
import com.gtsoft.meddyl.customer.system.gtsoft.DotNetDateDeserializer;
import com.gtsoft.meddyl.customer.system.gtsoft.ServiceHandler;
import java.util.Date;


public class REST_CustomerService
{
	private String web_service;
	private Gson gson;

	private JSONResponse json_response;
	private JSONSuccessfulResponse json_successful_response;
	private JSONErrorResponse json_error_response;


	public REST_CustomerService(String _web_service)
	{
		web_service = _web_service;

		GsonBuilder builder = new GsonBuilder();
		builder.registerTypeAdapter(Date.class, new DotNetDateDeserializer());
		gson = builder.create();
	}

	public void Get_Application_Settings(Login_Log login_log_obj)
	{
		String uri = "system/application_settings";

		JsonElement je = gson.toJsonTree(login_log_obj);
		JsonObject jo = new JsonObject();
		jo.add("login_log_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), Application_Type.class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Get_System_Settings(System_Settings system_settings_obj)
	{
		String uri = "system/system_settings";

		JsonElement je = gson.toJsonTree(system_settings_obj);
		JsonObject jo = new JsonObject();
		jo.add("system_settings_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), System_Settings.class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Get_Industry_Parent_Level(Industry industry_obj)
	{
		String uri = "system/industry/parent";

		JsonElement je = gson.toJsonTree(industry_obj);
		JsonObject jo = new JsonObject();
		jo.add("industry_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), Industry[].class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Register(Customer customer_obj)
	{
		String uri = "customer/register";

		JsonElement je = gson.toJsonTree(customer_obj);
		JsonObject jo = new JsonObject();
		jo.add("customer_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), Customer.class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Login(Customer customer_obj)
	{
		String uri = "customer/login";

		JsonElement je = gson.toJsonTree(customer_obj);
		JsonObject jo = new JsonObject();
		jo.add("customer_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), Customer.class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Forgot_Password(Customer customer_obj)
	{
		String uri = "customer/forgot_password";

		JsonElement je = gson.toJsonTree(customer_obj);
		JsonObject jo = new JsonObject();
		jo.add("customer_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Login_With_Facebook(Customer customer_obj)
	{
		String uri = "customer/login_facebook";

		JsonElement je = gson.toJsonTree(customer_obj);
		JsonObject jo = new JsonObject();
		jo.add("customer_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), Customer.class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Get_Customer_Profile(Customer customer_obj)
	{
		String uri = "customer/profile";

		JsonElement je = gson.toJsonTree(customer_obj);
		JsonObject jo = new JsonObject();
		jo.add("customer_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), Customer.class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Update_Customer(Customer customer_obj)
	{
		String uri = "customer/update";

		JsonElement je = gson.toJsonTree(customer_obj);
		JsonObject jo = new JsonObject();
		jo.add("customer_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Update_Customer_Settings(Customer customer_obj)
	{
		String uri = "customer/update_settings";

		JsonElement je = gson.toJsonTree(customer_obj);
		JsonObject jo = new JsonObject();
		jo.add("customer_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Add_Credit_Card(Credit_Card credit_card_obj)
	{
		String uri = "customer/credit_card/add";

		JsonElement je = gson.toJsonTree(credit_card_obj);
		JsonObject jo = new JsonObject();
		jo.add("credit_card_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), Credit_Card.class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Delete_Credit_Card(Credit_Card credit_card_obj)
	{
		String uri = "customer/credit_card/delete";

		JsonElement je = gson.toJsonTree(credit_card_obj);
		JsonObject jo = new JsonObject();
		jo.add("credit_card_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Set_Default_Credit_Card(Credit_Card credit_card_obj)
	{
		String uri = "customer/credit_card/set_default";

		JsonElement je = gson.toJsonTree(credit_card_obj);
		JsonObject jo = new JsonObject();
		jo.add("credit_card_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Get_Credit_Cards(Customer customer_obj)
	{
		String uri = "customer/credit_card/get_all";

		JsonElement je = gson.toJsonTree(customer_obj);
		JsonObject jo = new JsonObject();
		jo.add("customer_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), Credit_Card[].class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Get_Valid_Promotions(Customer customer_obj)
	{
		String uri = "customer/valid_promotions";

		JsonElement je = gson.toJsonTree(customer_obj);
		JsonObject jo = new JsonObject();
		jo.add("customer_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), Promotion_Activity[].class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Get_Deals(int customer_id, float latitude, float longitude)
	{
		String uri = "deal/deals/?customer_id=" + customer_id + "&latitude=" + latitude + "&longitude=" + longitude;


		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.GET, "");

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), Deal[].class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Get_Deal_Detail(Certificate certificate_obj)
	{
		String uri = "deal/deal_detail";

		JsonElement je = gson.toJsonTree(certificate_obj);
		JsonObject jo = new JsonObject();
		jo.add("certificate_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), Deal.class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Buy_Certificate(Certificate_Payment certificate_payment_obj)
	{
		String uri = "deal/certificate/buy";

		JsonElement je = gson.toJsonTree(certificate_payment_obj);
		JsonObject jo = new JsonObject();
		jo.add("certificate_payment_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), Certificate_Payment.class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Get_Customer_Active_Certificates(Customer customer_obj)
	{
		String uri = "deal/certificates/active";

		JsonElement je = gson.toJsonTree(customer_obj);
		JsonObject jo = new JsonObject();
		jo.add("customer_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), Certificate[].class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Get_Customer_Certificates(Customer customer_obj)
	{
		String uri = "deal/certificates";

		JsonElement je = gson.toJsonTree(customer_obj);
		JsonObject jo = new JsonObject();
		jo.add("customer_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), Certificate[].class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Get_Certificate_Detail(Certificate certificate_obj)
	{
		String uri = "deal/certificate";

		JsonElement je = gson.toJsonTree(certificate_obj);
		JsonObject jo = new JsonObject();
		jo.add("certificate_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), Certificate.class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Get_Payment(Certificate certificate_obj)
	{
		String uri = "deal/get_payment";

		JsonElement je = gson.toJsonTree(certificate_obj);
		JsonObject jo = new JsonObject();
		jo.add("certificate_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), Certificate_Payment.class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}

	public void Apply_Promotion(Certificate_Payment certificate_payment_obj)
	{
		String uri = "deal/apply_promotion";

		JsonElement je = gson.toJsonTree(certificate_payment_obj);
		JsonObject jo = new JsonObject();
		jo.add("certificate_payment_obj", je);
		String json_request_string = jo.toString();

		ServiceHandler sh = new ServiceHandler();
		String json_response_string = sh.makeServiceCall(web_service + uri, ServiceHandler.POST, json_request_string);

		if(json_response_string == null)
		{
			No_Internet_Connection();
		}
		else
		{
			JsonObject jsonObject = gson.fromJson(json_response_string, JsonObject.class);
			json_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONResponse.class);

			if(json_response.getSuccessful())
			{
				json_successful_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONSuccessfulResponse.class);
				json_successful_response.setDataObj(gson.fromJson(((JsonObject)jsonObject.get("JSONResponse")).get("data_obj"), Certificate_Payment.class));
			}
			else
			{
				json_error_response = gson.fromJson(jsonObject.get("JSONResponse"), JSONErrorResponse.class);
			}
		}
	}


	private void No_Internet_Connection()
	{
		json_response = new JSONResponse();
		json_response.setSuccessful(false);

		System_Error system_error_obj = new System_Error();
		system_error_obj.setCode(500);
		system_error_obj.setMessage("No Internet Connectin");

		json_error_response = new JSONErrorResponse();
		json_error_response.setSuccessful(json_response.getSuccessful());
		json_error_response.setSystemError(system_error_obj);
	}


	public JSONResponse getJSONResponse()
	{
		return this.json_response;
	}

	public JSONSuccessfulResponse getJSONSuccessfulResponse()
	{
		return this.json_successful_response;
	}

	public JSONErrorResponse getJSONErrorResponse()
	{
		return this.json_error_response;
	}
}

