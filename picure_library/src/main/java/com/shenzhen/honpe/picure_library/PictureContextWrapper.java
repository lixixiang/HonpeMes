package com.shenzhen.honpe.picure_library;

import android.content.Context;
import android.content.ContextWrapper;

import com.shenzhen.honpe.picure_library.language.PictureLanguageUtils;


/**
 * @author：luck
 * @date：2019-12-15 19:34
 * @describe：ContextWrapper
 */
public class PictureContextWrapper extends ContextWrapper {

    public PictureContextWrapper(Context base) {
        super(base);
    }

    public static ContextWrapper wrap(Context context, int language) {
        PictureLanguageUtils.setAppLanguage(context, language);
        return new PictureContextWrapper(context);
    }
}
