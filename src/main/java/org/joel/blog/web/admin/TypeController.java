package org.joel.blog.web.admin;

import org.joel.blog.pojo.Type;
import org.joel.blog.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class TypeController {
    private TypeService typeService;

    @Autowired
    public void setTypeService(TypeService typeService) {
        this.typeService = typeService;
    }

    // 获取types列表并跳转到分类主页
    @GetMapping("/types")
    public String types(@PageableDefault(size = 3, sort = {"id"}, direction = Sort.Direction.ASC) Pageable pageable,
                        Model model) {
        Page<Type> typePage = typeService.listType(pageable);
        model.addAttribute("page", typePage);
        return "admin/types";
    }

    // 新增页面
    @GetMapping("/types/insert")
    public String insert(Model model) {
        model.addAttribute("type", new Type());
        // 新增和修改共用一个页面，需要通过传入的Type是否有id去控制提交按钮对应的接口
        return "admin/types-input";
    }

    // 修改页面
    @GetMapping("/types/update/{id}")
    public String update(@PathVariable Long id, Model model) {
        model.addAttribute("type", typeService.getType(id));
        return "admin/types-input";
    }

    // 新增接口
    @PostMapping("/types/insert")
    public String insert(Type type,
                         RedirectAttributes attributes,
                         BindingResult result) {
        if (typeService.getTypeByName(type.getName()) != null) {
            result.rejectValue("name", "nameError", "不能添加重复的分类");
            return "admin/types-input";
        }
        if (typeService.saveType(type) == null) {
            attributes.addFlashAttribute("message", "新增失败");
        } else {
            attributes.addFlashAttribute("message", "新增成功");
        }
        return "redirect:/admin/types";
    }

    // 修改接口
    @PostMapping("/types/update/{id}")
    public String update(Type type,
                         RedirectAttributes attributes,
                         @PathVariable Long id,
                         BindingResult result) {
        if (typeService.getTypeByName(type.getName()) != null) {
            result.rejectValue("name", "nameError", "不能修改为重复的分类");
            return "admin/types-input";
        }
        if (typeService.updateType(id, type) == null) {
            attributes.addFlashAttribute("message", "更新失败");
        } else {
            attributes.addFlashAttribute("message", "更新成功");
        }
        return "redirect:/admin/types";
    }


    @GetMapping("/types/delete/{id}")
    public String delete(@PathVariable Long id, RedirectAttributes attributes) {
        typeService.deleteType(id);
        attributes.addFlashAttribute("message", "删除成功");
        return "redirect:/admin/types";
    }
}
