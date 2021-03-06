/*
 * Licensed to DuraSpace under one or more contributor license agreements.
 * See the NOTICE file distributed with this work for additional information
 * regarding copyright ownership.
 *
 * DuraSpace licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except in
 * compliance with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.fcrepo.http.commons.exceptionhandlers;

import org.slf4j.Logger;

import javax.jcr.version.LabelExistsVersionException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static javax.ws.rs.core.Response.Status.CONFLICT;
import static javax.ws.rs.core.Response.status;
import static org.slf4j.LoggerFactory.getLogger;

/**
 *  Translate LabelExistsVersionException errors into reasonable
 *  HTTP error codes
 *
 * @author md5wz
 * @since 11/21/14
 */
@Provider
public class LabelExistsVersionExceptionMapper implements
        ExceptionMapper<LabelExistsVersionException>, ExceptionDebugLogging {

    private static final Logger LOGGER =
        getLogger(LabelExistsVersionExceptionMapper.class);

    @Override
    public Response toResponse(final LabelExistsVersionException e) {
        LOGGER.error("LabelExistsVersionException intercepted by LabelExistsVersionExceptionMapper: {}\n",
                    e.getMessage());
        debugException(this, e, LOGGER);
        return status(CONFLICT).entity(e.getMessage()).build();
    }
}
