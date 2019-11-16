package com.schoolofnet.springGradle;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

@RestController
@RequestMapping("/")
public class HelloWorld {

	@GetMapping
	public String sayHello() {
		return "Hello from SpringBoot by School of net !";
	}
	
	@GetMapping("/subpatch")
	public String subPatch() {
		return "Eu sou um subpatch !";
	}
	
	@GetMapping("/subpatchrequest")
	public String subPatchRequest(@RequestParam("name") String name) {
		return "This is one subPatch of endpoint /" + name;
	}
	
	@GetMapping("/subpatch/{dynamic}")
	public String subpatchDynamic(@PathVariable("dynamic") String name) {
		return "Hello " + name + " this is my route";
	}
	
	@GetMapping("/subpatchwebrequest")
	public String subPatchWebRequest(final WebRequest webRequest) {
		String name = webRequest.getParameter("name");
		if (name != null) {
			return "This is one subpatch of endpoint /" + name;
		}
		return "Param not defined !";
	}
	
	@GetMapping("/subpatchrequired")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public String subpatchRequired(@RequestParam(value = "name", required = false) String name) {
		return "This is one subPath of endpoint /" + name;
	}
	
	@GetMapping("/subpatchrequiredbody")
	@ResponseBody
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public String subpatchRequiredBody(@RequestParam(value = "name", required = false) String name) {
		return "This is one subPath of endpoint /" + name;
	}
	
	@PostMapping("/post")
	public String sayHelloPost(@RequestBody Map<String, Object> payload) {
		return payload.get("name").toString();
	}
}
