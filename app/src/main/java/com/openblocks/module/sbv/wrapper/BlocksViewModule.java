package com.openblocks.module.sbv.wrapper;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.openblocks.blocks.view.Block;
import com.openblocks.blocks.view.BlocksView;
import com.openblocks.blocks.view.BlocksViewEvent;
import com.openblocks.blocks.view.BlockField;
import com.openblocks.moduleinterface.OpenBlocksModule;
import com.openblocks.moduleinterface.callbacks.Logger;
import com.openblocks.moduleinterface.callbacks.SaveCallback;
import com.openblocks.moduleinterface.models.OpenBlocksProjectMetadata;
import com.openblocks.moduleinterface.models.code.BlockCode;
import com.openblocks.moduleinterface.models.config.OpenBlocksConfig;
import com.openblocks.moduleinterface.projectfiles.OpenBlocksCode;
import com.openblocks.moduleinterface.projectfiles.OpenBlocksLayout;

import java.lang.ref.WeakReference;
import java.util.ArrayList;

public class BlocksViewModule implements OpenBlocksModule.ProjectCodeGUI {

    WeakReference<Context> context;
    Logger l;

    @Override
    public Type getType() {
        return Type.PROJECT_CODE_GUI;
    }

    @Override
    public void initialize(Context context, Logger logger) {
        this.context = new WeakReference<>(context);
        l = logger;
    }

    @Override
    public OpenBlocksConfig setupConfig() {
        return null;
    }

    @Override
    public void applyConfig(OpenBlocksConfig config) {

    }

    @Override
    public void show(Context context, ViewGroup layout, OpenBlocksCode code_data, OpenBlocksLayout layout_data, OpenBlocksProjectMetadata metadata, SaveCallback<OpenBlocksCode> saveCallback) {
        BlocksViewEvent event = new BlocksViewEvent(metadata.getName(), "onCreate");

        for (BlockCode block : code_data.blocks) {
            ArrayList<BlockField> arguments = new ArrayList<>();

            for (String parameter : block.parameters) {
                arguments.add(new BlockField(parameter));
            }

            event.blocks.add(new Block(
                    code_data.blocks_formats.get(block.opcode),
                    arguments,
                    block.color
            ));
        }

        // TODO: 3/28/21 Set this to the code
        // For testing, we use the demo blocks first

        layout.addView(new BlocksView(context));
    }
}
