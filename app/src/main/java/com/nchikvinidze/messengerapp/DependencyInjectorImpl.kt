package com.nchikvinidze.messengerapp

import ge.mbabutsidze.todoapp.todoList.ListRepository
import ge.mbabutsidze.todoapp.todoList.ListRepositoryImpl

class DependencyInjectorImpl : DependencyInjector {
  override fun listRepository() : ListRepository {
    return ListRepositoryImpl()
  }
}
