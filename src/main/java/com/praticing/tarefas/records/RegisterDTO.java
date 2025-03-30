package com.praticing.tarefas.records;

import com.praticing.tarefas.enums.UserRole;

public record RegisterDTO(String login,String password, UserRole role) {

}
