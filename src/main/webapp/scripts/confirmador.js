/**
 * @author Flavio Oliveira
 */

function confirmar(idcon){
	let res = confirm("Tem certeza que deseja excluir este contato?")
	res ? window.location.href = "delete?idcon=" + idcon : {};
}