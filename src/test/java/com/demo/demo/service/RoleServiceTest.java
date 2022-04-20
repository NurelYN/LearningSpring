package com.demo.demo.service;

import com.demo.demo.entity.Role;
import com.demo.demo.exception.RecordNotFoundException;
import com.demo.demo.repository.RoleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.management.relation.RoleNotFoundException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    private RoleService roleService;

    @BeforeEach
    public void setUp(){
        roleService = new RoleService(roleRepository);
    }

    @Test
    public void verifyFindAll(){
        roleService.findAll();
        verify(roleRepository,times(1)).findAll();
    }

    @Test
    public void verifyFindByName(){
        String name = "Gerogi";
        when(roleRepository.findByName(name))
                .thenReturn(Optional.of(Role.builder().build()));
        roleService.findByName(name);
        verify(roleRepository,times(1)).findByName(name);
    }

    @Test
    public void verifyFindByNameRecordNotFoundException(){
        String exceptedMessage = "Role Georgi is not found";
        Throwable ex = assertThrows(RecordNotFoundException.class,()->{
            roleService.findByName("Georgi");
        });
        assertEquals(exceptedMessage,ex.getMessage()); // checking if two ex are equals
    }

    @Test
    public void verifyFindById(){
        Long id = 5L;
        when(roleRepository.findById(id))
                .thenReturn(Optional.of(Role.builder().id(id).build()));
         Role actualRole = roleService.findById(id);
         assertEquals(actualRole.getId(),id);
        verify(roleRepository,times(1)).findById(id);
    }

    @Test
    public void verifyFindByIdRecordNotFoundException(){
        String expectedMessage="Role with id:1 is not found";
        Throwable ex = assertThrows(RecordNotFoundException.class,()->{
            roleService.findById(1L);
        });
        assertEquals(expectedMessage,ex.getMessage());
    }

    @Test
    public void verifyUpdateThrowsExceptionWhenIdIsNull(){
        assertThrows(NullPointerException.class,()->{
            roleService.update(Role.builder().build(), null);
        });
    }

    @Test
    public void verifyUpdateThrowsExceptionWhenUpdateRoleIsNull(){
        assertThrows(NullPointerException.class,()->{
            roleService.update(null, 5L);
        });
    }

    @Test
    public void verifyUpdateSucces(){
        Role expectedRole = Role.builder()
                .id(5L)
                .name("Georgi")
                .build();
        when(roleRepository.findById(expectedRole.getId()))
                .thenReturn(Optional.of(Role.builder().id(expectedRole.getId()).name("Ivan").build()));

        Role actualResult = roleService.update(expectedRole,expectedRole.getId());

        verify(roleRepository,times(1)).findById(expectedRole.getId());
        assertEquals(expectedRole.getName(),actualResult.getName());
    }
}
