package com.openblocks.module.sbv.wrapper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.widget.Button;

import com.openblocks.moduleinterface.OpenBlocksModule;
import com.openblocks.moduleinterface.callbacks.Logger;
import com.openblocks.moduleinterface.models.code.BlockCode;
import com.openblocks.moduleinterface.models.code.ParseBlockTask;
import com.openblocks.moduleinterface.models.config.OpenBlocksConfig;
import com.openblocks.moduleinterface.projectfiles.OpenBlocksCode;

public class OpenBlocksCollection implements OpenBlocksModule.BlocksCollection {
    @Override public Type getType() { return Type.BLOCKS_COLLECTION; }
    @Override public void initialize(Context context, Logger logger) { }
    @Override public OpenBlocksConfig setupConfig() {
        return null;
    }
    @Override public void applyConfig(OpenBlocksConfig config) { }
    @Override public OpenBlocksCode initializeNewCode() {
        return null;
    }

    @Override
    public Object[] getBlocks() {
        return new Object[] {
                new Object[] {
                        /* Opcode */ "toast",
                        /* Format */ "Toast %s",
                        (ParseBlockTask) (code, parameters, childs_parsed_code) ->
                                code.append("android.widget.Toast.makeText(this, \"").append(parameters.get(0)).append("\", android.widget.Toast.LENGTH_SHORT).show();")
                },
                new Object[] {
                        "on_click",
                        "View ID %v onClick",
                        (ParseBlockTask) (code, parameters, childs_parsed_code) -> {
                            String view_id = parameters.get(0);

                            code.append(
                                    "findViewById(R.id." + view_id + ")" +
                                    ".setOnClickListener(new android.view.View.OnClickListener() {\n" +
                                    "    @Override\n" +
                                    "    public void onClick(android.view.View v) {\n" +
                                             childs_parsed_code + "\n" +
                                    "    }\n" +
                                    "});"
                            );
                        }
                }
        };
    }
}
