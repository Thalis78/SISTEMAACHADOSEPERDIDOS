package br.edu.ifpi.catce.sistemaachadoseperdidos.controller;

import br.edu.ifpi.catce.sistemaachadoseperdidos.model.AlunoModel;
import br.edu.ifpi.catce.sistemaachadoseperdidos.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class AdmController {
    @Autowired
    AlunoRepository alunoRepository;

    @GetMapping("/indexadm")
    public String paginaPrincipal(){
        return "/adm/index";
    }

    @GetMapping("/buscaradm")
    public String buscar(Model model){
        model.addAttribute(new AlunoModel());
        return "adm/buscar";
    }
    @GetMapping("/resultadoBuscaAdm")
    public String buscaRealizada(@ModelAttribute AlunoModel alunoModel, Model model, RedirectAttributes redirectAttributes){
        List<AlunoModel> alunos = alunoRepository.findByPrimeiroNome(alunoModel.getNome());
        if(alunos.size() > 0){
            model.addAttribute("stylePersonalizado","display : none;");
            model.addAttribute("display","display: table-row");
            model.addAttribute("nome","Nome");
            model.addAttribute("documento","Documento");
            model.addAttribute("data","Data");
            model.addAttribute("acao","Ação");
            model.addAttribute(alunos);
        } else{
            redirectAttributes.addFlashAttribute("mensagem","Não existe pendências com esse nome");
            redirectAttributes.addFlashAttribute("style","mensagemErro");
            return "redirect:/buscaradm";
        }

        return "adm/buscar";

    }
    @GetMapping("/loginadm")
    public String login(){
        return "/adm/login";
    }


    @GetMapping("/cadastraradm")
    public String cadastrar(Model model){
        model.addAttribute(new AlunoModel());
        return "/adm/cadastrar";
    }
    @GetMapping("/confirmar")
    public String confirmar(@RequestParam("id") Long id, Model model){
        model.addAttribute("src","static/icons/de-volta.png");
        model.addAttribute("id",id);
        return "/adm/confirmar";
    }
    @PostMapping("/deletar")
    public String deletar(@RequestParam("id") Long id, RedirectAttributes redirectAttributes){
        alunoRepository.deleteById(id);
        redirectAttributes.addFlashAttribute("mensagem","Apagado com sucesso.");
        redirectAttributes.addFlashAttribute("style","mensagemSucesso");
        return "redirect:/buscaradm";
    }
}
