package com.lighting.gallery.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class GalleryDTO {
	private String tblphotopostseq; // tblPhotoPostSeq
	private String photofilename; // photoFileName
	private String tblmemberseq; // tblAttachedPhotoSeq (추가)
	private String postpate;

}
