import SwiftUI
import Client

struct ContentView: View {
    @ObservedObject var viewModel = TodoViewModel()

	var body: some View {
        VStack {
            List {
                if viewModel.todos.isEmpty {
                    Text("Pull down to refresh")
                } else {
                    ForEach(viewModel.todos) { item in
                        TodoItemView(todo: item) { viewModel.delete(item: item) }
                    }.onDelete { indices in
                        viewModel.deleteAll(with: indices)
                    }
                }

                AddItemView { title in
                        viewModel.addItem(titled: title)
                }
            }.refreshable {
                viewModel.fetch()
            }.onAppear {
                viewModel.fetch()
            }
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

            // Button(action: delete) { Image(systemName: "trash") }
        }
    }
}

struct AddItemView: View {
    let submit: (String) -> ()

    @State private var text = ""

    var body: some View {
        HStack {
            TextField("Add item...", text: $text).onSubmit {
                submit(text)
                text = ""
            }
            Button(action: {
                submit(text)
                text = ""
            }) { Image(systemName: "checkmark") }
        }
    }
}

extension IdentifiableEntity: Identifiable { }

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
