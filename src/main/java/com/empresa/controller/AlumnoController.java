package com.empresa.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.empresa.entity.Alumno;
import com.empresa.service.AlumnoService;

import lombok.extern.apachecommons.CommonsLog;

@CommonsLog//funciona con el lombok
@RestController
@RequestMapping("/rest/alumno")
public class AlumnoController {
	
	@Autowired
	private AlumnoService service;
	
	@GetMapping
	public ResponseEntity<List<Alumno>> lista(){
		System.out.println(">>inicio>>lista");
		List<Alumno> lstAlumno=service.listaAlumno();
		return ResponseEntity.ok(lstAlumno);
	}
	
	@PostMapping
	public ResponseEntity<Alumno> registra(@RequestBody Alumno obj){
		System.out.println(">>inicio>>registra"+obj.getNombre());
		Alumno objAlumno=service.insertaActualizaAlumno(obj);
		return ResponseEntity.ok(objAlumno);
	}
	
	
	@PutMapping
	public ResponseEntity<Alumno> actualiza(@RequestBody Alumno obj){
		System.out.println(">>> inicio >>> actualiza " + obj.getNombre());
		Alumno objAlumno = service.insertaActualizaAlumno(obj);
		return ResponseEntity.ok(objAlumno);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Alumno> elimina(@PathVariable("id") int idAlumno){
		System.out.println(">>> inicio >>> elimina "+idAlumno );
		Optional<Alumno> optAlumno = service.obtienePorId(idAlumno);
		if(optAlumno.isPresent()) {
			service.eliminaAlumno(idAlumno);
			return ResponseEntity.ok(optAlumno.get());
		}else {
			System.out.println(">>> No existe alumno "+idAlumno );
			return ResponseEntity.badRequest().build();
		}
	} 
	
	@GetMapping("/{dni}")
	public ResponseEntity<List<Alumno>> buscapordni(@PathVariable("dni") String dni){
		System.out.println(">>> inicio >>> lista " );
		List<Alumno> optAlumno = service.listaPorDni(dni);
		
			service.listaPorDni(dni);
			return ResponseEntity.ok(optAlumno);
		
		}
	} 

