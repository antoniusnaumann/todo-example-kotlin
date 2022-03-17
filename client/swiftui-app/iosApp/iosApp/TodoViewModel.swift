import Foundation
import Client

class TodoViewModel: ObservableObject {
   @Published var todos: [TodoItem] = []

    private let todoService: TodoItemService
    init(_ todoService: TodoItemService = TodoItemService(baseUrl: "http://localhost:8080", client: HttpClientKt.defaultClient())) {
        self.todoService = todoService
    }

    func fetch() {
        todoService.fetch { items, error in
            self.todos = items ?? self.todos
        }
    }

    func delete(item: TodoItem) {
        todoService.delete(item: item) { success, error in
            if success == true {
                self.todos.removeAll { $0.id == item.id }
            }
        }
    }

    func deleteAll(with indices: IndexSet) {
        indices.forEach { index in
            delete(item: todos[index])
        }
    }

    func addItem(titled title: String) {
        let description = "Created in SwiftUI app"
        todoService.create(item: TodoItem(id: -1, title: title, details: description)) { id, error in
            guard let id = id else { return }

            self.todos += [TodoItem(id: Int32(truncating: id), title: title, details: description)]
        }
    }
}
