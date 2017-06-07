package io.gabbm.ticket_office.api.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/api")
@Api(value="onlinestore", description="Operations pertaining to products in Online Store")
public class ApiRestController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/open/test")
	@ApiOperation(value = "View a list of available products", response = Iterable.class)
	@ApiResponses(value = {
	        @ApiResponse(code = 200, message = "Successfully retrieved list"),
	        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
	        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
	        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
	}
	)
	public void openTest(){
		logger.info("entering test rest");
	}
	
	@RequestMapping("/admin/test")
	public void adminTest(){
		logger.info("entering test rest");
	}
}
