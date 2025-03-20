from PIL import Image

def replace_black_with_transparency(image_path, output_path):
    # 打开图片并转换为 RGBA 模式
    image = Image.open(image_path).convert("RGBA")
    data = image.getdata()

    # 将更深的黑色像素替换为透明
    new_data = []
    for item in data:
        # 判断是否为更深的黑色（RGB 值都小于 30）
        if item[0] < 5 and item[1] < 5 and item[2] < 5:
            new_data.append((255, 255, 255, 0))  # 替换为透明
        else:
            new_data.append(item)  # 保留原像素

    # 更新图片数据
    image.putdata(new_data)

    # 裁剪掉上下部分各 20%
    width, height = image.size
    crop_top = int(height * 0.3)  # 上部分裁掉 20%
    crop_bottom = int(height * 0.7)  # 下部分裁掉 20%

    # 裁剪图片
    cropped_image = image.crop((0, crop_top, width, crop_bottom))

    # 保存处理后的图片
    cropped_image.save(output_path, "PNG")
    print(f"处理后的图片已保存到: {output_path}")

# 使用示例
replace_black_with_transparency("iokays.jpg", "iokays.png")