package com.demo.demo.service;

import com.demo.demo.entity.Role;
import com.demo.demo.exception.DuplicateRecordException;
import com.demo.demo.exception.RecordNotFoundException;
import com.demo.demo.repository.RoleRepository;
import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
@Transactional
public class RoleService {

    private RoleRepository roleRepository;

    public Role findByName(String name){
       return roleRepository.findByName(name)
               .orElseThrow(()->new RecordNotFoundException(String.format("Role %s is not found",name)));
    }

    public Set<Role> findAll(){
        return new HashSet<>(roleRepository.findAll()); // zashtoto findall methoda po default wrushta list
    }

    public Role findById(Long id){
        return roleRepository.findById(id).orElseThrow(()->new RecordNotFoundException(
                String.format("Role with id:%s is not found",id)));
    }
    
    public Role update(@NonNull Role updatedRole,@NonNull Long id){


        Role dbRole = findById(id);
        dbRole.setName(updatedRole.getName());
        return save(dbRole);
    }

    public void delete(Long id){
        roleRepository.deleteById(id);
    }

    public void delete(String name){
        roleRepository.deleteByName(name);
    }


    public Role save(Role role){
        try{
            return roleRepository.save(role);
        } catch(DataIntegrityViolationException ex){
            throw new DuplicateRecordException(String.format("Role %s already exist", role.getName()));
        }

    }
}
