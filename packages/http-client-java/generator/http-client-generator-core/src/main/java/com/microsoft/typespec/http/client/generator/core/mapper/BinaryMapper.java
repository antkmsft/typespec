// Copyright (c) Microsoft Corporation. All rights reserved.
// Licensed under the MIT License.

package com.microsoft.typespec.http.client.generator.core.mapper;

import com.microsoft.typespec.http.client.generator.core.extension.model.codemodel.BinarySchema;
import com.microsoft.typespec.http.client.generator.core.extension.plugin.JavaSettings;
import com.microsoft.typespec.http.client.generator.core.model.clientmodel.ClassType;
import com.microsoft.typespec.http.client.generator.core.model.clientmodel.GenericType;
import com.microsoft.typespec.http.client.generator.core.model.clientmodel.IType;

/**
 * A mapper that maps a {@link BinarySchema} to {@link ClassType#BINARY_DATA} when the schema isn't null.
 */
public class BinaryMapper implements IMapper<BinarySchema, IType> {

    private static final BinaryMapper INSTANCE = new BinaryMapper();

    /**
     * Gets the global {@link BinaryMapper} instance.
     *
     * @return The global {@link BinaryMapper} instance.
     */
    public static BinaryMapper getInstance() {
        return INSTANCE;
    }

    @Override
    public IType map(BinarySchema binarySchema) {
        if (binarySchema == null) {
            return null;
        }
        // Not touching vanilla for now. Storage is still using Flux<ByteBuffer>.
        return JavaSettings.getInstance().isVanilla() ? GenericType.FLUX_BYTE_BUFFER : ClassType.BINARY_DATA;
    }
}
