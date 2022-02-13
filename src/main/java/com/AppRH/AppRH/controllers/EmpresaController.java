package com.AppRH.AppRH.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.AppRH.AppRH.models.Candidato;
import com.AppRH.AppRH.models.Dependente;
import com.AppRH.AppRH.models.Empresa;
import com.AppRH.AppRH.models.Funcionario;
import com.AppRH.AppRH.models.Vaga;
import com.AppRH.AppRH.repository.EmpresaRepository;

@Controller
public class EmpresaController {
	
	@Autowired
	private EmpresaRepository er;
	
	// GET que chama o form para cadastrar empresa
	@RequestMapping("/cadastrarEmpresa")
	public String form() {
		return "empresa/form-empresa";
	}
	
	// POST que cadastra empresas
	@RequestMapping(value = "/cadastrarEmpresa", method = RequestMethod.POST)
	public String form(@Valid Empresa empresa, BindingResult result, RedirectAttributes attributes) {

		if (result.hasErrors()) {
			attributes.addFlashAttribute("mensagem", "Verifique os campos");
			return "redirect:/cadastrarEmpresa";
		}

		er.save(empresa);
		attributes.addFlashAttribute("mensagem", "Funcionário cadastrado com sucesso!");
		return "redirect:/cadastrarEmpresa";
	}
	
	// GET que lista empresas
		@RequestMapping("/empresas")
		public ModelAndView listaEmpresas() {
			ModelAndView mv = new ModelAndView("empresa/lista-empresa");
			Iterable<Empresa> empresas = er.findAll();
			mv.addObject("empresas", empresas);
			return mv;
		}
		
		// GET detalhes das empresas
		@RequestMapping("/detalhes-empresa/{id}")
		public ModelAndView detalhesEmpresas(@PathVariable("id") long id) {
			Empresa empresa = er.findById(id);
			ModelAndView mv = new ModelAndView("empresa/detalhes-empresa");
			mv.addObject("empresas", empresa);

			return mv;

		}
		
		// Métodos que atualizam empresa
		// GET que chama o FORM de edição da empresa
		@RequestMapping("/editar-empresa")
		public ModelAndView editarEmpresa(long id) {
			Empresa empresa = er.findById(id);
			ModelAndView mv = new ModelAndView("empresa/update-empresa");
			mv.addObject("empresa", empresa);
			return mv;
		}
		
		// POST que atualiza o empresa
		@RequestMapping(value = "/editar-empresa", method = RequestMethod.POST)
		public String updateEmpresa(@Valid Empresa empresa,  BindingResult result, RedirectAttributes attributes){
			
			er.save(empresa);
			attributes.addFlashAttribute("success", "Empresa alterada com sucesso!");
			
			long idLong = empresa.getId();
			String id = "" + idLong;
			return "redirect:/detalhes-empresa/" + id;
			
		}
	
}
