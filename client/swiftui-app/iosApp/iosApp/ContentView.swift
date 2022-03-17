import SwiftUI
import Client

struct ContentView: View {
    @ObservedObject var viewModel = TodoViewModel()

	var body: some View {
        List {
            if viewModel.todos.isEmpty {
                Text("Pull down to refresh")
            } else {
                ForEach(viewModel.todos) {
                    TodoItemView(todo: $0)
                }
            }
        }.refreshable {
            viewModel.fetch()
        }
	}
}

struct TodoItemView: View {
    let todo: TodoItem

    var body: some View {
        VStack(alignment: .leading) {
            Text(todo.title).font(.headline)
            Text(todo.details)
        }
    }
}

extension TodoItem: Identifiable { }

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
