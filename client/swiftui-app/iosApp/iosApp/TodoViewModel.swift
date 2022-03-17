//
//  TodoViewModel.swift
//  iosApp
//
//  Created by Antonius Naumann on 17.03.22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import Client

class TodoViewModel: ObservableObject {
   @Published var todos: [TodoItem] = []

    private let todoService: TodoItemService
    init(_ todoService: TodoItemService = TodoItemService(baseUrl: "http://localhost:8080", client: HttpClientKt.defaultClient())) {
        self.todoService = todoService
    }

    func fetch() {
        todoService.fetchTodos { items, error in
            self.todos = items ?? self.todos
        }
    }
}
