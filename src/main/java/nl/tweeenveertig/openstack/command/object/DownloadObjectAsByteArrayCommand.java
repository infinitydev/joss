package nl.tweeenveertig.openstack.command.object;

import nl.tweeenveertig.openstack.client.impl.AccountImpl;
import nl.tweeenveertig.openstack.command.identity.access.Access;
import nl.tweeenveertig.openstack.client.Container;
import nl.tweeenveertig.openstack.client.StoredObject;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.util.EntityUtils;

import java.io.*;

public class DownloadObjectAsByteArrayCommand extends AbstractDownloadObjectCommand<HttpGet, byte[]> {

    private byte[] result;

    public DownloadObjectAsByteArrayCommand(AccountImpl account, HttpClient httpClient, Access access, Container container, StoredObject object) {
        super(account, httpClient, access, container, object);
    }

    @Override
    protected void handleEntity(HttpEntity entity) throws IOException {
        result = EntityUtils.toByteArray(entity);
    }

    @Override
    protected String getMd5() throws IOException {
        return DigestUtils.md5Hex(result);
    }

    @Override
    protected byte[] getObjectAsReturnObject() {
        return result;
    }
}