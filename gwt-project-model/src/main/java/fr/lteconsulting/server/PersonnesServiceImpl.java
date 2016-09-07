package fr.lteconsulting.server;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.server.rpc.RPC;
import com.google.gwt.user.server.rpc.RPCRequest;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

public class PersonnesServiceImpl extends RemoteServiceServlet
{
	private static final long serialVersionUID = 8040896818489712249L;

	private static PersonnesRepository repository = new PersonnesRepository();

	@Override
	public String processCall( String payload ) throws SerializationException
	{
		// First, check for possible XSRF situation
		checkPermutationStrongName();

		RPCRequest rpcRequest = null;
		try
		{
			rpcRequest = RPC.decodeRequest( payload, repository.getClass(), this );

			Object result = rpcRequest.getMethod().invoke( repository, rpcRequest.getParameters() );

			return RPC.encodeResponseForSuccess( rpcRequest.getMethod(), result, rpcRequest.getSerializationPolicy(), rpcRequest.getFlags() );

		}
		catch( Exception ex )
		{
			log( "An " + ex.getClass().getSimpleName() + " was thrown while processing this call.", ex );
			return RPC.encodeResponseForFailedRequest( rpcRequest, ex );
		}
	}
}
