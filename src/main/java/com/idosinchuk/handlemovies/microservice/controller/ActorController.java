package com.idosinchuk.handlemovies.microservice.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedResources;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.idosinchuk.handlemovies.microservice.dto.ActorRequestDTO;
import com.idosinchuk.handlemovies.microservice.dto.ActorResponseDTO;
import com.idosinchuk.handlemovies.microservice.service.ActorService;

import io.micrometer.core.annotation.Timed;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * Controller for handle actors
 * 
 * @author Igor Dosinchuk
 *
 */
@RestController
@Timed
@Api(value = "Actor API Rest")
@RequestMapping({ "api/v1" })
public class ActorController {

	@Autowired
	public ActorService actorService;

	/**
	 * Retrieve list of all actors according to the search criteria.
	 * 
	 * @param pageable paging fields
	 * @return List of actors found with support hateoas and pagination
	 */
	@SuppressWarnings({ "rawtypes" })
	@GetMapping(path = "/actors", produces = MediaType.APPLICATION_JSON_VALUE)
	@Timed
	@ResponseBody
	@ApiOperation(value = "Retrieve list of all actors.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 204, message = "No Content"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Internal Error"),
			@ApiResponse(code = 503, message = "Service Unavailable") })
	public ResponseEntity<PagedResources<ActorResponseDTO>> getActors(Pageable pageable,
			PagedResourcesAssembler assembler) {

		return actorService.getActors(pageable, assembler);

	}

	/**
	 * Retrieve details of a actor by the id.
	 * 
	 * @param id actor id
	 * @return Information of the actor
	 */
	@GetMapping(path = "/actors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Retrieve details of an actor by the id.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 204, message = "No Content"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Internal Error"),
			@ApiResponse(code = 503, message = "Service Unavailable") })
	public ResponseEntity<ActorResponseDTO> getActors(@PathVariable Long id) {

		return actorService.getActor(id);
	}

	/**
	 * Add a actor.
	 * 
	 * @param ActorResponseDTO object to save
	 * @return response with status and ActorResponseDTO
	 */
	@PostMapping(path = "/actors", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Add an actor.")
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Created"),
			@ApiResponse(code = 204, message = "No Content"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Internal Error"),
			@ApiResponse(code = 503, message = "Service Unavailable") })
	public ResponseEntity<ActorResponseDTO> addActors(@Valid @RequestBody ActorRequestDTO actorRequestDTO) {

		return actorService.addActor(actorRequestDTO);

	}

	/**
	 * Delete a actor by the id.
	 * 
	 * @param id actor id
	 * @return response with status
	 */
	@DeleteMapping(path = "/actors/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	@ApiOperation(value = "Delete an actor by the id.")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success"),
			@ApiResponse(code = 204, message = "No Content"), @ApiResponse(code = 400, message = "Bad Request"),
			@ApiResponse(code = 404, message = "Not Found"), @ApiResponse(code = 500, message = "Internal Error"),
			@ApiResponse(code = 503, message = "Service Unavailable") })
	public ResponseEntity<ActorResponseDTO> deleteActors(@PathVariable Long id) {

		return actorService.deleteActor(id);

	}
}
