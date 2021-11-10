import javax.swing.Action

data class Command(val name: String, val desc: String, val action: ( List<String>) -> Unit){
}
