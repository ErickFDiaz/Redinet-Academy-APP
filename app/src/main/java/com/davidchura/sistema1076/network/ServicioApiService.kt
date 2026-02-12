import com.davidchura.sistema1076.model.Cliente
import com.davidchura.sistema1076.model.Pedido
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ServicioApiService {
    @GET("clientes.php") suspend fun getClientes(): Response<List<Cliente>>

    @GET("pedidoscliente.php")
    suspend fun getPedidosCliente(@Query("idcliente") idCliente: String): Response<List<Pedido>>
}
