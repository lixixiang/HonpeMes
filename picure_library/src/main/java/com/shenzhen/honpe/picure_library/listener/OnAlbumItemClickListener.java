package com.shenzhen.honpe.picure_library.listener;


import com.shenzhen.honpe.picure_library.entity.LocalMedia;

import java.util.List;

/**
 * @author：luck
 * @date：2020-03-26 10:57
 * @describe：OnAlbumItemClickListener
 */
public interface OnAlbumItemClickListener {
    /**
     * Album catalog item click event
     *
     * @param position
     * @param isCameraFolder
     * @param bucketId
     * @param folderName
     * @param data
     */
    void onItemClick(int position, boolean isCameraFolder,
                     long bucketId, String folderName, List<LocalMedia> data);
}
