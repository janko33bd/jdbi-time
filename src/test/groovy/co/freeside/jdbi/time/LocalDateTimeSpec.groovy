/*
 * Copyright 2014 Rob Fletcher
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package co.freeside.jdbi.time

import java.time.LocalDateTime
import org.skife.jdbi.v2.tweak.ResultSetMapper

class LocalDateTimeSpec extends MapsToStringSpecification<LocalDateTime> {

    def setup() {
        handle.registerArgumentFactory new TemporalAsStringArgumentFactory(LocalDateTime)
    }

    @Override
    protected Class<LocalDateTime> targetType() {
        LocalDateTime
    }

    @Override
    protected ResultSetMapper<LocalDateTime> targetTypeMapperFor(String name) {
        new LocalDateTimeMapper(name)
    }

    @Override
    protected ResultSetMapper<LocalDateTime> targetTypeMapperForFirst() {
        LocalDateTimeMapper.FIRST
    }

    @Override
    protected LocalDateTime targetValue() {
        LocalDateTime.now()
    }

    @Override
    protected String toColumnType(LocalDateTime value) {
        value.toString()
    }
}
