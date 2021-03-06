/*
 * Copyright (c) 2013, MyCat_Plus and/or its affiliates. All rights reserved.
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
package io.mycat.route.function;

import io.mycat.config.model.rule.RuleAlgorithm;

/**
 * number column partion by Mod operator
 * if count is 10 then 0 to 0,21 to 1 (21 % 10 =1)
 * @author wuzhih
 *
 */
public class PartitionByMod extends AbstractPartitionAlgorithm implements RuleAlgorithm {

    private static final long serialVersionUID = 1L;

    public PartitionByMod() {
        name = "ColumnValueMod";
    }

    @Override
    public Integer calculate(String columnValue) {
        try {
            int intValue = Long.valueOf(columnValue).intValue();
            if (intValue < 0) {
                intValue = -intValue;
            }
            int node = intValue % partitionNum;
            return node;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(
                String.format("Eliminate any quote and non number within it,%s", columnValue), e);
        }

    }

}