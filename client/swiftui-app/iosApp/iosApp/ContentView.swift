import SwiftUI
import Client

struct ContentView: View {
    let todo = Greeting().exampleTodoItem

	var body: some View {
        VStack {
            Text(todo.title).font(.headline)
            Text(todo.details)
        }
	}
}

struct ContentView_Previews: PreviewProvider {
	static var previews: some View {
		ContentView()
	}
}
