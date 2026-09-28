import java.util.*;

public class Main {

static Scanner sc = new Scanner(System.in);

static class Mecanico {
String nome, cpf, especialidade, telefone;
Box box;

Mecanico(String nome, String cpf, String especialidade, String telefone) {
this.nome = nome;
this.cpf = cpf;
this.especialidade = especialidade;
this.telefone = telefone;
}
}

static class Box {
int numero, capacidade;
String tipo, localizacao;
Mecanico mecanico;
ArrayList<Ordem> ordens = new ArrayList<>();

Box(int numero, String tipo, int capacidade, String localizacao) {
this.numero = numero;
this.tipo = tipo;
this.capacidade = capacidade;
this.localizacao = localizacao;
}
}

static class Servico {
String nome, categoria;
int tempo;
double valor;

Servico(String nome, int tempo, double valor, String categoria) {
this.nome = nome;
this.tempo = tempo;
this.valor = valor;
this.categoria = categoria;
}
}

static class Ordem {
int codigo;
String cliente, modelo, placa, data, status;
double valor;
Box box;
ArrayList<Servico> servicos = new ArrayList<>();

Ordem(int codigo, String cliente, String modelo, String placa,
String data, String status, double valor) {
this.codigo = codigo;
this.cliente = cliente;
this.modelo = modelo;
this.placa = placa;
this.data = data;
this.status = status;
this.valor = valor;
}
}

static ArrayList<Mecanico> mecanicos = new ArrayList<>();
static ArrayList<Box> boxes = new ArrayList<>();
static ArrayList<Ordem> ordens = new ArrayList<>();

public static void main(String[] args) {

mecanicos.add(new Mecanico("Joao", "111", "Motor", "9999-1111"));
mecanicos.add(new Mecanico("Pedro", "222", "Freios", "9999-2222"));
mecanicos.add(new Mecanico("Carlos", "333", "Eletrica", "9999-3333"));

boxes.add(new Box(1, "Motor", 2, "Area A"));
boxes.add(new Box(2, "Freios", 2, "Area B"));
boxes.add(new Box(3, "Eletrica", 1, "Area C"));

int op;

do {
System.out.println("\n1 - Cadastrar ordem");
System.out.println("2 - Associar mecanico");
System.out.println("3 - Atribuir ordem ao box");
System.out.println("4 - Exibir ordens do box");
System.out.println("5 - Ordens finalizadas por box");
System.out.println("6 - Buscar por status");
System.out.println("7 - Detalhes da ordem");
System.out.println("0 - Sair");
System.out.print("Opcao: ");

op = sc.nextInt();
sc.nextLine();

switch (op) {
case 1:
cadastrarOrdem();
break;

case 2:
associarMecanico();
break;

case 3:
atribuirOrdem();
break;

case 4:
exibirOrdensBox();
break;

case 5:
quantidadeFinalizadas();
break;

case 6:
buscarStatus();
break;

case 7:
detalhesOrdem();
break;

case 0:
System.out.println("Programa encerrado.");
break;

default:
System.out.println("Opcao invalida.");
}

} while (op != 0);
}

static void cadastrarOrdem() {

System.out.print("Codigo: ");
int codigo = sc.nextInt();
sc.nextLine();

System.out.print("Cliente: ");
String cliente = sc.nextLine();

System.out.print("Modelo: ");
String modelo = sc.nextLine();

System.out.print("Placa: ");
String placa = sc.nextLine();

System.out.print("Data: ");
String data = sc.nextLine();

System.out.print("Status: ");
String status = sc.nextLine();

System.out.print("Valor estimado: ");
double valor = sc.nextDouble();
sc.nextLine();

Ordem ordem = new Ordem(
codigo,
cliente,
modelo,
placa,
data,
status,
valor
);

System.out.print("Nome do servico: ");
String nome = sc.nextLine();

System.out.print("Tempo estimado: ");
int tempo = sc.nextInt();

System.out.print("Valor do servico: ");
double valorServico = sc.nextDouble();
sc.nextLine();

System.out.print("Categoria: ");
String categoria = sc.nextLine();

ordem.servicos.add(
new Servico(nome, tempo, valorServico, categoria)
);

ordens.add(ordem);

System.out.println("Ordem cadastrada.");
}

static void associarMecanico() {

for (int i = 0; i < mecanicos.size(); i++) {
System.out.println(
(i + 1) + " - " + mecanicos.get(i).nome
);
}

System.out.print("Mecanico: ");
int m = sc.nextInt() - 1;

if (m < 0 || m >= mecanicos.size()) {
System.out.println("Mecanico invalido.");
return;
}

for (Box b : boxes) {
System.out.println(
b.numero + " - " + b.tipo
);
}

System.out.print("Box: ");
int numero = sc.nextInt();

Box boxEscolhido = null;

for (Box b : boxes) {
if (b.numero == numero) {
boxEscolhido = b;
break;
}
}

if (boxEscolhido == null) {
System.out.println("Box nao encontrado.");
return;
}

if (mecanicos.get(m).box != null) {
System.out.println("Mecanico ja possui um box.");
return;
}

if (boxEscolhido.mecanico != null) {
System.out.println("Box ja possui um mecanico.");
return;
}

mecanicos.get(m).box = boxEscolhido;
boxEscolhido.mecanico = mecanicos.get(m);

System.out.println("Associacao realizada.");
}

static void atribuirOrdem() {

System.out.print("Codigo da ordem: ");
int codigo = sc.nextInt();

Ordem ordem = null;

for (Ordem o : ordens) {
if (o.codigo == codigo) {
ordem = o;
break;
}
}

if (ordem == null) {
System.out.println("Ordem nao encontrada.");
return;
}

if (ordem.status.equalsIgnoreCase("aberta")) {
System.out.println("Ordem aberta nao possui box.");
return;
}

if (ordem.box != null) {
System.out.println("Ordem ja possui um box.");
return;
}

if (ordem.servicos.isEmpty()) {
System.out.println("Ordem nao possui servico.");
return;
}

System.out.print("Numero do box: ");
int numero = sc.nextInt();

Box boxEscolhido = null;

for (Box b : boxes) {
if (b.numero == numero) {
boxEscolhido = b;
break;
}
}

if (boxEscolhido == null) {
System.out.println("Box nao encontrado.");
return;
}

if (boxEscolhido.ordens.size() >= boxEscolhido.capacidade) {
System.out.println("Box atingiu sua capacidade maxima.");
return;
}

Servico servico = ordem.servicos.get(0);

if (!servico.categoria.equalsIgnoreCase(boxEscolhido.tipo)) {
System.out.println(
"O servico nao corresponde ao tipo do box."
);
return;
}

ordem.box = boxEscolhido;
boxEscolhido.ordens.add(ordem);

System.out.println("Ordem atribuida.");
}

static void exibirOrdensBox() {

System.out.print("Numero do box: ");
int numero = sc.nextInt();

for (Box b : boxes) {

if (b.numero == numero) {

for (Ordem o : b.ordens) {
System.out.println(
"Ordem " + o.codigo +
" - " + o.status
);
}

System.out.println(
"Total: " + b.ordens.size()
);

return;
}
}

System.out.println("Box nao encontrado.");
}

static void quantidadeFinalizadas() {

System.out.print("Numero do box: ");
int numero = sc.nextInt();

for (Box b : boxes) {

if (b.numero == numero) {

int total = 0;

for (Ordem o : b.ordens) {

if (o.status.equalsIgnoreCase("finalizada")) {
total++;
}
}

System.out.println(
"Finalizadas: " + total
);

return;
}
}

System.out.println("Box nao encontrado.");
}

static void buscarStatus() {

sc.nextLine();

System.out.print("Status: ");
String status = sc.nextLine();

boolean encontrou = false;

for (Ordem o : ordens) {

if (o.status.equalsIgnoreCase(status)) {
mostrar(o);
encontrou = true;
}
}

if (!encontrou) {
System.out.println("Nenhuma ordem encontrada.");
}
}

static void detalhesOrdem() {

System.out.print("Codigo da ordem: ");
int codigo = sc.nextInt();

for (Ordem o : ordens) {

if (o.codigo == codigo) {
mostrar(o);
return;
}
}

System.out.println("Ordem nao encontrada.");
}

static void mostrar(Ordem o) {

System.out.println("\nCodigo: " + o.codigo);
System.out.println("Cliente: " + o.cliente);
System.out.println("Veiculo: " + o.modelo);
System.out.println("Placa: " + o.placa);
System.out.println("Data: " + o.data);
System.out.println("Status: " + o.status);
System.out.println("Valor estimado: R$ " + o.valor);

if (o.box != null) {

System.out.println(
"Box: " + o.box.numero
);

System.out.println(
"Tipo do box: " + o.box.tipo
);

if (o.box.mecanico != null) {
System.out.println(
"Mecanico: " +
o.box.mecanico.nome
);
}
} else {
System.out.println("Box: Nenhum");
}

for (Servico s : o.servicos) {

System.out.println(
"Servico: " + s.nome
);

System.out.println(
"Tempo estimado: " + s.tempo
);

System.out.println(
"Valor do servico: R$ " + s.valor
);

System.out.println(
"Categoria: " + s.categoria
);
}
}
}
