package com.demo1.rest.webservices.restfulwebservices.exception;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserJPAResource {
	
	@Autowired
	private UserDaoService service;
	@Autowired
	private UserRepository userRepository;
	@GetMapping("/jpa/users")
	public List<User> retrieveAllUsers()
	{
		return userRepository.findAll();
	}

	//modified
	@GetMapping("/jpa/users/{id}")
/*	public Resource<User> retrieveUser(@PathVariable int id){
		Optional<User> user=userRepository.findById(id);
		if(user==null) {
			throw new UserNotFoundException("id-"+id);
		}
		Resource<User> resource=new Resource<User>(user);
		ControllerLinkBuilder linkTo=linkTo(methodOn(this.getClass()).retrieveAllUsers());
		resource.add(linkTo.withRel("all-users"));
		
		return resource;
	}
	*/

/*	@GetMapping("/users/{id}")
	public EntityModel<User> retrieveUser(@PathVariable int id)
	{
		User user=service.findOne(id);
		if(user==null)
		{
			throw new UserNotFoundException("id-"+id);
		}
		
		//HATEOAS
		EntityModel<User> model = new EntityModel<>(user);
		WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
		model.add(linkTo.withRel("all-users"));
		return model;
	}
	*/
	
	
	//accept details of the user
	//output-CREATED and return the created URI
	@PostMapping("/jpa/users")
	public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
	{
		User savedUser=service.save(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").
		buildAndExpand(savedUser.getId()).toUri();		
		return ResponseEntity.created(location).build();
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public String retrieveUserPosts(@PathVariable int id)
	{
		User user=service.findOne(id);
		if(user==null)
		{
			throw new UserNotFoundException("id-"+id);
		}

		return service.retrievePosts(id);
	}
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id)
	{
		User user=service.deleteById(id);
		if(user==null)
		{
			throw new UserNotFoundException("id-"+id);
		}
	}

}
