import SwiftUI
import Client

struct ContentView: View {
    @ObservedObject var viewModel = TodoViewModel()

	var body: some View {
        List {
            if viewModel.todos.isEmpty {
                Text("Pull down to refresh")
            } else {
                ForEach(viewModel.todos) { item in
                    TodoItemView(todo: item) { viewModel.delete(item: item) }
                }
            }
        }.refreshable {
            viewModel.fetch()
        }.onAppear {
            viewModel.fetch()
        }
	}
}

struct TodoItemView: View {
    let todo: TodoItem
    let delete: () -> ()

    var body: some View {
        HStack {
            VStack(alignment: .leading) {
                Text(todo.title).font(.headline)
                Text(todo.details)
            }
            
            Spacer()

            Button(action: delete) { Image(systemName: "trash") }
        }
    }
}

extension IdentifiableEntity: Identifiable { }

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
