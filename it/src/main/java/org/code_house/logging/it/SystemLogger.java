package org.code_house.logging.it;

import org.code_house.logging.api.Code;
import org.code_house.logging.api.ReplaceableLogger;
import org.code_house.logging.api.level.Info;
import org.code_house.logging.api.message.Message;

@Info
@Code("SYS")
public interface SystemLogger extends ReplaceableLogger {

    @Message("Method {} do not ship @Code")
    @Code("03") void noMethodCode(String msg);

}