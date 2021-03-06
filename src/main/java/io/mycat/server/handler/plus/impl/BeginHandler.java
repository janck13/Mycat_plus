/*
 * Copyright (c) 2018, MyCat_Plus and/or its affiliates. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software;Designed and Developed mainly by many Chinese 
 * opensource volunteers. you can redistribute it and/or modify it under the 
 * terms of the GNU General Public License version 2 only, as published by the
 * Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 * 
 * Any questions about this component can be directed to it's project Web address 
 * https://code.google.com/p/opencloudb/.
 *
 */

package io.mycat.server.handler.plus.impl;

import io.mycat.net.plus.ClientConn;
import io.mycat.server.handler.plus.SQLHandler;

/**
 * 处理begin
 * @author jeff.cao
 * @version 0.0.1, 2018年4月26日 上午11:41:42 
 */
public class BeginHandler implements SQLHandler {

    private static final byte[] AC_OFF = new byte[] { 7, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0 };

    @Override
    public void handle(String stmt, ClientConn c) {
        if (c.isAutocommit()) {
            c.write(c.writeToBuffer(AC_OFF, c.allocate()));
        } else {
            c.getSession2().commit();
        }
        c.setAutocommit(false);
    }

    @Override
    public int type() {
        return SQLHandler.Type.BEGIN;
    }

}
