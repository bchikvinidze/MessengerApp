package com.nchikvinidze.messengerapp

import ge.mbabutsidze.todoapp.todoList.ListRepository

interface DependencyInjector {
  fun listRepository() : ListRepository
}
