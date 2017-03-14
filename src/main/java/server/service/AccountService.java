package server.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import server.obj.Account;

@Path("/accountservice/")
@Api(value = "/accountservice/", description = "Sample JAX-RS service with Swagger documentation")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class AccountService {

	Map<String, Account> accounts = new HashMap<String, Account>();

	public void init() {

		Account newAccount1 = new Account();
		newAccount1.setId(1);
		newAccount1.setName("Alvin Reyes");

		Account newAccount2 = new Account();
		newAccount2.setId(2);
		newAccount2.setName("Rachelle Ann de Guzman Reyes");

		accounts.put("1", newAccount1);
		accounts.put("2", newAccount2);

	}

	public AccountService() {
		init();
	}

	@POST
	@Path("/accounts/{id}/")
	  @ApiOperation(
		        value = "Post operation with Id in a body",
		        notes = "Post operation with Id in a body",
		        response = Account.class)

	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Given Account user found"),
			    @ApiResponse(code = 404, message = "Given Account  not found"),
			    @ApiResponse(code = 500, message = "Internal server error due to encoding the data"),
			    @ApiResponse(code = 400, message = "Bad request due to decoding the data"),
			    @ApiResponse(code = 412, message = "Pre condition failed due to required data not found") })
	public Account getAccount( @ApiParam(value = "id", required = true)  @PathParam("id") String id) {
		Account c = accounts.get(id);
		if(c == null){
			
		}
			
		return c;
	}

	@GET
	@Path("/accounts/getall")
	 @ApiOperation(
		        value = "GET operation for all Accounts",
		        notes = "GET operation for all Accounts",
		        response = Account.class,
		        responseContainer="List"
		    )

	 @ApiResponses(value = { @ApiResponse(code = 200, message = "Accounts retrieved"),
			    @ApiResponse(code = 404, message = "No accounts found"),
			    @ApiResponse(code = 500, message = "Internal server error due to encoding the data"),
			    @ApiResponse(code = 400, message = "Bad request due to decoding the data"),
			    @ApiResponse(code = 412, message = "Pre condition failed due to required data not found") })
	public Response getAllAccounts() {
		System.out.println("calling method");
		init();
		/**
		 for (int i = 0; i < accounts.size(); i++) {
			 accountList.add(accounts.get(i));
				System.out.println("calling method, adding accoiunt");
			}
			**/
		
		  List<Account> workshops = new ArrayList<Account>();//a list of entities
		  Iterator it = accounts.entrySet().iterator();
		    while (it.hasNext()) {
		        Map.Entry pair = (Map.Entry)it.next();
		        System.out.println(pair.getKey() + " = " + pair.getValue());
		        System.out.println("calling method, adding accoiunt and ID" + ((Account) pair.getValue()).getId());
		        workshops.add((Account) pair.getValue());
		        
		    }
		  
		  GenericEntity<List<Account>> list = new GenericEntity<List<Account>>(workshops) {
			        };
			        System.out.println("List element, ID " + list.getEntity().get(0).getId());
			        return Response.ok(list).build();
	       // return Response.ok(accountList).build();
		
	}

}