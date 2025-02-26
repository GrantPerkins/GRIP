package edu.wpi.grip.generated.opencv_imgproc.enumeration;

import org.bytedeco.opencv.global.opencv_imgproc;

public enum ColorConversionCodesEnum {

  /**
   * add alpha channel to RGB or BGR image
   */
  COLOR_BGR2BGRA(opencv_imgproc.COLOR_BGR2BGRA), COLOR_RGB2RGBA(opencv_imgproc.COLOR_RGB2RGBA),
  /**
   * remove alpha channel from RGB or BGR image
   */
  COLOR_BGRA2BGR(opencv_imgproc.COLOR_BGRA2BGR), COLOR_RGBA2RGB(opencv_imgproc.COLOR_RGBA2RGB),
  /**
   * convert between RGB and BGR color spaces (with or without alpha channel)
   */
  COLOR_BGR2RGBA(opencv_imgproc.COLOR_BGR2RGBA), COLOR_RGB2BGRA(opencv_imgproc.COLOR_RGB2BGRA), COLOR_RGBA2BGR(opencv_imgproc.COLOR_RGBA2BGR), COLOR_BGRA2RGB(opencv_imgproc.COLOR_BGRA2RGB), COLOR_BGR2RGB(opencv_imgproc.COLOR_BGR2RGB), COLOR_RGB2BGR(opencv_imgproc.COLOR_RGB2BGR), COLOR_BGRA2RGBA(opencv_imgproc.COLOR_BGRA2RGBA), COLOR_RGBA2BGRA(opencv_imgproc.COLOR_RGBA2BGRA),
  /**
   * convert between RGB/BGR and grayscale, @ref color_convert_rgb_gray "color conversions"
   */
  COLOR_BGR2GRAY(opencv_imgproc.COLOR_BGR2GRAY), COLOR_RGB2GRAY(opencv_imgproc.COLOR_RGB2GRAY), COLOR_GRAY2BGR(opencv_imgproc.COLOR_GRAY2BGR), COLOR_GRAY2RGB(opencv_imgproc.COLOR_GRAY2RGB), COLOR_GRAY2BGRA(opencv_imgproc.COLOR_GRAY2BGRA), COLOR_GRAY2RGBA(opencv_imgproc.COLOR_GRAY2RGBA), COLOR_BGRA2GRAY(opencv_imgproc.COLOR_BGRA2GRAY), COLOR_RGBA2GRAY(opencv_imgproc.COLOR_RGBA2GRAY),
  /**
   * convert between RGB/BGR and BGR565 (16-bit images)
   */
  COLOR_BGR2BGR565(opencv_imgproc.COLOR_BGR2BGR565), COLOR_RGB2BGR565(opencv_imgproc.COLOR_RGB2BGR565), COLOR_BGR5652BGR(opencv_imgproc.COLOR_BGR5652BGR), COLOR_BGR5652RGB(opencv_imgproc.COLOR_BGR5652RGB), COLOR_BGRA2BGR565(opencv_imgproc.COLOR_BGRA2BGR565), COLOR_RGBA2BGR565(opencv_imgproc.COLOR_RGBA2BGR565), COLOR_BGR5652BGRA(opencv_imgproc.COLOR_BGR5652BGRA), COLOR_BGR5652RGBA(opencv_imgproc.COLOR_BGR5652RGBA),
  /**
   * convert between grayscale to BGR565 (16-bit images)
   */
  COLOR_GRAY2BGR565(opencv_imgproc.COLOR_GRAY2BGR565), COLOR_BGR5652GRAY(opencv_imgproc.COLOR_BGR5652GRAY),
  /**
   * convert between RGB/BGR and BGR555 (16-bit images)
   */
  COLOR_BGR2BGR555(opencv_imgproc.COLOR_BGR2BGR555), COLOR_RGB2BGR555(opencv_imgproc.COLOR_RGB2BGR555), COLOR_BGR5552BGR(opencv_imgproc.COLOR_BGR5552BGR), COLOR_BGR5552RGB(opencv_imgproc.COLOR_BGR5552RGB), COLOR_BGRA2BGR555(opencv_imgproc.COLOR_BGRA2BGR555), COLOR_RGBA2BGR555(opencv_imgproc.COLOR_RGBA2BGR555), COLOR_BGR5552BGRA(opencv_imgproc.COLOR_BGR5552BGRA), COLOR_BGR5552RGBA(opencv_imgproc.COLOR_BGR5552RGBA),
  /**
   * convert between grayscale and BGR555 (16-bit images)
   */
  COLOR_GRAY2BGR555(opencv_imgproc.COLOR_GRAY2BGR555), COLOR_BGR5552GRAY(opencv_imgproc.COLOR_BGR5552GRAY),
  /**
   * convert RGB/BGR to CIE XYZ, @ref color_convert_rgb_xyz "color conversions"
   */
  COLOR_BGR2XYZ(opencv_imgproc.COLOR_BGR2XYZ), COLOR_RGB2XYZ(opencv_imgproc.COLOR_RGB2XYZ), COLOR_XYZ2BGR(opencv_imgproc.COLOR_XYZ2BGR), COLOR_XYZ2RGB(opencv_imgproc.COLOR_XYZ2RGB),
  /**
   * convert RGB/BGR to luma-chroma (aka YCC), @ref color_convert_rgb_ycrcb "color conversions"
   */
  COLOR_BGR2YCrCb(opencv_imgproc.COLOR_BGR2YCrCb), COLOR_RGB2YCrCb(opencv_imgproc.COLOR_RGB2YCrCb), COLOR_YCrCb2BGR(opencv_imgproc.COLOR_YCrCb2BGR), COLOR_YCrCb2RGB(opencv_imgproc.COLOR_YCrCb2RGB),
  /**
   * convert RGB/BGR to HSV (hue saturation value), @ref color_convert_rgb_hsv "color conversions"
   */
  COLOR_BGR2HSV(opencv_imgproc.COLOR_BGR2HSV), COLOR_RGB2HSV(opencv_imgproc.COLOR_RGB2HSV),
  /**
   * convert RGB/BGR to CIE Lab, @ref color_convert_rgb_lab "color conversions"
   */
  COLOR_BGR2Lab(opencv_imgproc.COLOR_BGR2Lab), COLOR_RGB2Lab(opencv_imgproc.COLOR_RGB2Lab),
  /**
   * convert RGB/BGR to CIE Luv, @ref color_convert_rgb_luv "color conversions"
   */
  COLOR_BGR2Luv(opencv_imgproc.COLOR_BGR2Luv), COLOR_RGB2Luv(opencv_imgproc.COLOR_RGB2Luv),
  /**
   * convert RGB/BGR to HLS (hue lightness saturation), @ref color_convert_rgb_hls "color
   * conversions"
   */
  COLOR_BGR2HLS(opencv_imgproc.COLOR_BGR2HLS), COLOR_RGB2HLS(opencv_imgproc.COLOR_RGB2HLS),
  /**
   * backward conversions to RGB/BGR
   */
  COLOR_HSV2BGR(opencv_imgproc.COLOR_HSV2BGR), COLOR_HSV2RGB(opencv_imgproc.COLOR_HSV2RGB), COLOR_Lab2BGR(opencv_imgproc.COLOR_Lab2BGR), COLOR_Lab2RGB(opencv_imgproc.COLOR_Lab2RGB), COLOR_Luv2BGR(opencv_imgproc.COLOR_Luv2BGR), COLOR_Luv2RGB(opencv_imgproc.COLOR_Luv2RGB), COLOR_HLS2BGR(opencv_imgproc.COLOR_HLS2BGR), COLOR_HLS2RGB(opencv_imgproc.COLOR_HLS2RGB), COLOR_BGR2HSV_FULL(opencv_imgproc.COLOR_BGR2HSV_FULL), COLOR_RGB2HSV_FULL(opencv_imgproc.COLOR_RGB2HSV_FULL), COLOR_BGR2HLS_FULL(opencv_imgproc.COLOR_BGR2HLS_FULL), COLOR_RGB2HLS_FULL(opencv_imgproc.COLOR_RGB2HLS_FULL), COLOR_HSV2BGR_FULL(opencv_imgproc.COLOR_HSV2BGR_FULL), COLOR_HSV2RGB_FULL(opencv_imgproc.COLOR_HSV2RGB_FULL), COLOR_HLS2BGR_FULL(opencv_imgproc.COLOR_HLS2BGR_FULL), COLOR_HLS2RGB_FULL(opencv_imgproc.COLOR_HLS2RGB_FULL), COLOR_LBGR2Lab(opencv_imgproc.COLOR_LBGR2Lab), COLOR_LRGB2Lab(opencv_imgproc.COLOR_LRGB2Lab), COLOR_LBGR2Luv(opencv_imgproc.COLOR_LBGR2Luv), COLOR_LRGB2Luv(opencv_imgproc.COLOR_LRGB2Luv), COLOR_Lab2LBGR(opencv_imgproc.COLOR_Lab2LBGR), COLOR_Lab2LRGB(opencv_imgproc.COLOR_Lab2LRGB), COLOR_Luv2LBGR(opencv_imgproc.COLOR_Luv2LBGR), COLOR_Luv2LRGB(opencv_imgproc.COLOR_Luv2LRGB),
  /**
   * convert between RGB/BGR and YUV
   */
  COLOR_BGR2YUV(opencv_imgproc.COLOR_BGR2YUV), COLOR_RGB2YUV(opencv_imgproc.COLOR_RGB2YUV), COLOR_YUV2BGR(opencv_imgproc.COLOR_YUV2BGR), COLOR_YUV2RGB(opencv_imgproc.COLOR_YUV2RGB),
  /**
   * YUV 4:2:0 family to RGB
   */
  COLOR_YUV2RGB_NV12(opencv_imgproc.COLOR_YUV2RGB_NV12), COLOR_YUV2BGR_NV12(opencv_imgproc.COLOR_YUV2BGR_NV12), COLOR_YUV2RGB_NV21(opencv_imgproc.COLOR_YUV2RGB_NV21), COLOR_YUV2BGR_NV21(opencv_imgproc.COLOR_YUV2BGR_NV21), COLOR_YUV420sp2RGB(opencv_imgproc.COLOR_YUV420sp2RGB), COLOR_YUV420sp2BGR(opencv_imgproc.COLOR_YUV420sp2BGR), COLOR_YUV2RGBA_NV12(opencv_imgproc.COLOR_YUV2RGBA_NV12), COLOR_YUV2BGRA_NV12(opencv_imgproc.COLOR_YUV2BGRA_NV12), COLOR_YUV2RGBA_NV21(opencv_imgproc.COLOR_YUV2RGBA_NV21), COLOR_YUV2BGRA_NV21(opencv_imgproc.COLOR_YUV2BGRA_NV21), COLOR_YUV420sp2RGBA(opencv_imgproc.COLOR_YUV420sp2RGBA), COLOR_YUV420sp2BGRA(opencv_imgproc.COLOR_YUV420sp2BGRA), COLOR_YUV2RGB_YV12(opencv_imgproc.COLOR_YUV2RGB_YV12), COLOR_YUV2BGR_YV12(opencv_imgproc.COLOR_YUV2BGR_YV12), COLOR_YUV2RGB_IYUV(opencv_imgproc.COLOR_YUV2RGB_IYUV), COLOR_YUV2BGR_IYUV(opencv_imgproc.COLOR_YUV2BGR_IYUV), COLOR_YUV2RGB_I420(opencv_imgproc.COLOR_YUV2RGB_I420), COLOR_YUV2BGR_I420(opencv_imgproc.COLOR_YUV2BGR_I420), COLOR_YUV420p2RGB(opencv_imgproc.COLOR_YUV420p2RGB), COLOR_YUV420p2BGR(opencv_imgproc.COLOR_YUV420p2BGR), COLOR_YUV2RGBA_YV12(opencv_imgproc.COLOR_YUV2RGBA_YV12), COLOR_YUV2BGRA_YV12(opencv_imgproc.COLOR_YUV2BGRA_YV12), COLOR_YUV2RGBA_IYUV(opencv_imgproc.COLOR_YUV2RGBA_IYUV), COLOR_YUV2BGRA_IYUV(opencv_imgproc.COLOR_YUV2BGRA_IYUV), COLOR_YUV2RGBA_I420(opencv_imgproc.COLOR_YUV2RGBA_I420), COLOR_YUV2BGRA_I420(opencv_imgproc.COLOR_YUV2BGRA_I420), COLOR_YUV420p2RGBA(opencv_imgproc.COLOR_YUV420p2RGBA), COLOR_YUV420p2BGRA(opencv_imgproc.COLOR_YUV420p2BGRA), COLOR_YUV2GRAY_420(opencv_imgproc.COLOR_YUV2GRAY_420), COLOR_YUV2GRAY_NV21(opencv_imgproc.COLOR_YUV2GRAY_NV21), COLOR_YUV2GRAY_NV12(opencv_imgproc.COLOR_YUV2GRAY_NV12), COLOR_YUV2GRAY_YV12(opencv_imgproc.COLOR_YUV2GRAY_YV12), COLOR_YUV2GRAY_IYUV(opencv_imgproc.COLOR_YUV2GRAY_IYUV), COLOR_YUV2GRAY_I420(opencv_imgproc.COLOR_YUV2GRAY_I420), COLOR_YUV420sp2GRAY(opencv_imgproc.COLOR_YUV420sp2GRAY), COLOR_YUV420p2GRAY(opencv_imgproc.COLOR_YUV420p2GRAY),
  /**
   * YUV 4:2:2 family to RGB
   */
  COLOR_YUV2RGB_UYVY(opencv_imgproc.COLOR_YUV2RGB_UYVY), COLOR_YUV2BGR_UYVY(opencv_imgproc.COLOR_YUV2BGR_UYVY), COLOR_YUV2RGB_Y422(opencv_imgproc.COLOR_YUV2RGB_Y422), COLOR_YUV2BGR_Y422(opencv_imgproc.COLOR_YUV2BGR_Y422), COLOR_YUV2RGB_UYNV(opencv_imgproc.COLOR_YUV2RGB_UYNV), COLOR_YUV2BGR_UYNV(opencv_imgproc.COLOR_YUV2BGR_UYNV), COLOR_YUV2RGBA_UYVY(opencv_imgproc.COLOR_YUV2RGBA_UYVY), COLOR_YUV2BGRA_UYVY(opencv_imgproc.COLOR_YUV2BGRA_UYVY), COLOR_YUV2RGBA_Y422(opencv_imgproc.COLOR_YUV2RGBA_Y422), COLOR_YUV2BGRA_Y422(opencv_imgproc.COLOR_YUV2BGRA_Y422), COLOR_YUV2RGBA_UYNV(opencv_imgproc.COLOR_YUV2RGBA_UYNV), COLOR_YUV2BGRA_UYNV(opencv_imgproc.COLOR_YUV2BGRA_UYNV), COLOR_YUV2RGB_YUY2(opencv_imgproc.COLOR_YUV2RGB_YUY2), COLOR_YUV2BGR_YUY2(opencv_imgproc.COLOR_YUV2BGR_YUY2), COLOR_YUV2RGB_YVYU(opencv_imgproc.COLOR_YUV2RGB_YVYU), COLOR_YUV2BGR_YVYU(opencv_imgproc.COLOR_YUV2BGR_YVYU), COLOR_YUV2RGB_YUYV(opencv_imgproc.COLOR_YUV2RGB_YUYV), COLOR_YUV2BGR_YUYV(opencv_imgproc.COLOR_YUV2BGR_YUYV), COLOR_YUV2RGB_YUNV(opencv_imgproc.COLOR_YUV2RGB_YUNV), COLOR_YUV2BGR_YUNV(opencv_imgproc.COLOR_YUV2BGR_YUNV), COLOR_YUV2RGBA_YUY2(opencv_imgproc.COLOR_YUV2RGBA_YUY2), COLOR_YUV2BGRA_YUY2(opencv_imgproc.COLOR_YUV2BGRA_YUY2), COLOR_YUV2RGBA_YVYU(opencv_imgproc.COLOR_YUV2RGBA_YVYU), COLOR_YUV2BGRA_YVYU(opencv_imgproc.COLOR_YUV2BGRA_YVYU), COLOR_YUV2RGBA_YUYV(opencv_imgproc.COLOR_YUV2RGBA_YUYV), COLOR_YUV2BGRA_YUYV(opencv_imgproc.COLOR_YUV2BGRA_YUYV), COLOR_YUV2RGBA_YUNV(opencv_imgproc.COLOR_YUV2RGBA_YUNV), COLOR_YUV2BGRA_YUNV(opencv_imgproc.COLOR_YUV2BGRA_YUNV), COLOR_YUV2GRAY_UYVY(opencv_imgproc.COLOR_YUV2GRAY_UYVY), COLOR_YUV2GRAY_YUY2(opencv_imgproc.COLOR_YUV2GRAY_YUY2), COLOR_YUV2GRAY_Y422(opencv_imgproc.COLOR_YUV2GRAY_Y422), COLOR_YUV2GRAY_UYNV(opencv_imgproc.COLOR_YUV2GRAY_UYNV), COLOR_YUV2GRAY_YVYU(opencv_imgproc.COLOR_YUV2GRAY_YVYU), COLOR_YUV2GRAY_YUYV(opencv_imgproc.COLOR_YUV2GRAY_YUYV), COLOR_YUV2GRAY_YUNV(opencv_imgproc.COLOR_YUV2GRAY_YUNV),
  /**
   * alpha premultiplication
   */
  COLOR_RGBA2mRGBA(opencv_imgproc.COLOR_RGBA2mRGBA), COLOR_mRGBA2RGBA(opencv_imgproc.COLOR_mRGBA2RGBA),
  /**
   * RGB to YUV 4:2:0 family
   */
  COLOR_RGB2YUV_I420(opencv_imgproc.COLOR_RGB2YUV_I420), COLOR_BGR2YUV_I420(opencv_imgproc.COLOR_BGR2YUV_I420), COLOR_RGB2YUV_IYUV(opencv_imgproc.COLOR_RGB2YUV_IYUV), COLOR_BGR2YUV_IYUV(opencv_imgproc.COLOR_BGR2YUV_IYUV), COLOR_RGBA2YUV_I420(opencv_imgproc.COLOR_RGBA2YUV_I420), COLOR_BGRA2YUV_I420(opencv_imgproc.COLOR_BGRA2YUV_I420), COLOR_RGBA2YUV_IYUV(opencv_imgproc.COLOR_RGBA2YUV_IYUV), COLOR_BGRA2YUV_IYUV(opencv_imgproc.COLOR_BGRA2YUV_IYUV), COLOR_RGB2YUV_YV12(opencv_imgproc.COLOR_RGB2YUV_YV12), COLOR_BGR2YUV_YV12(opencv_imgproc.COLOR_BGR2YUV_YV12), COLOR_RGBA2YUV_YV12(opencv_imgproc.COLOR_RGBA2YUV_YV12), COLOR_BGRA2YUV_YV12(opencv_imgproc.COLOR_BGRA2YUV_YV12),
  /**
   * Demosaicing
   */
  COLOR_BayerBG2BGR(opencv_imgproc.COLOR_BayerBG2BGR), COLOR_BayerGB2BGR(opencv_imgproc.COLOR_BayerGB2BGR), COLOR_BayerRG2BGR(opencv_imgproc.COLOR_BayerRG2BGR), COLOR_BayerGR2BGR(opencv_imgproc.COLOR_BayerGR2BGR), COLOR_BayerBG2RGB(opencv_imgproc.COLOR_BayerBG2RGB), COLOR_BayerGB2RGB(opencv_imgproc.COLOR_BayerGB2RGB), COLOR_BayerRG2RGB(opencv_imgproc.COLOR_BayerRG2RGB), COLOR_BayerGR2RGB(opencv_imgproc.COLOR_BayerGR2RGB), COLOR_BayerBG2GRAY(opencv_imgproc.COLOR_BayerBG2GRAY), COLOR_BayerGB2GRAY(opencv_imgproc.COLOR_BayerGB2GRAY), COLOR_BayerRG2GRAY(opencv_imgproc.COLOR_BayerRG2GRAY), COLOR_BayerGR2GRAY(opencv_imgproc.COLOR_BayerGR2GRAY),
  /**
   * Demosaicing using Variable Number of Gradients
   */
  COLOR_BayerBG2BGR_VNG(opencv_imgproc.COLOR_BayerBG2BGR_VNG), COLOR_BayerGB2BGR_VNG(opencv_imgproc.COLOR_BayerGB2BGR_VNG), COLOR_BayerRG2BGR_VNG(opencv_imgproc.COLOR_BayerRG2BGR_VNG), COLOR_BayerGR2BGR_VNG(opencv_imgproc.COLOR_BayerGR2BGR_VNG), COLOR_BayerBG2RGB_VNG(opencv_imgproc.COLOR_BayerBG2RGB_VNG), COLOR_BayerGB2RGB_VNG(opencv_imgproc.COLOR_BayerGB2RGB_VNG), COLOR_BayerRG2RGB_VNG(opencv_imgproc.COLOR_BayerRG2RGB_VNG), COLOR_BayerGR2RGB_VNG(opencv_imgproc.COLOR_BayerGR2RGB_VNG),
  /**
   * Edge-Aware Demosaicing
   */
  COLOR_BayerBG2BGR_EA(opencv_imgproc.COLOR_BayerBG2BGR_EA), COLOR_BayerGB2BGR_EA(opencv_imgproc.COLOR_BayerGB2BGR_EA), COLOR_BayerRG2BGR_EA(opencv_imgproc.COLOR_BayerRG2BGR_EA), COLOR_BayerGR2BGR_EA(opencv_imgproc.COLOR_BayerGR2BGR_EA), COLOR_BayerBG2RGB_EA(opencv_imgproc.COLOR_BayerBG2RGB_EA), COLOR_BayerGB2RGB_EA(opencv_imgproc.COLOR_BayerGB2RGB_EA), COLOR_BayerRG2RGB_EA(opencv_imgproc.COLOR_BayerRG2RGB_EA), COLOR_BayerGR2RGB_EA(opencv_imgproc.COLOR_BayerGR2RGB_EA), COLOR_COLORCVT_MAX(opencv_imgproc.COLOR_COLORCVT_MAX);

  public final int value;

  ColorConversionCodesEnum(int value) {
    this.value = value;
  }
}
