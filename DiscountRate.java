public enum DiscountRate {
  NATIONALMERIT(1,10, "국가 유공자"),
  SOLDIER(2,5, "군인"),
  STUDENT(3,3, "학생"),
  COMMON(4,0, "일반");

  private Integer id;
  private Integer rate;
  private String name;

  DiscountRate(Integer id, Integer rate, String name) {
    this.id = id;
    this.rate = rate;
    this.name = name;
  }

  public Integer getId() {
    return this.id;
  }

  public Integer getRate() {
    return this.rate;
  }

  public String getName() {
    return this.name;
  }

  public static DiscountRate idCheck(Integer id) {
    for(DiscountRate dr : DiscountRate.values()) {
      if(id == dr.getId()) {
        return dr;
      }
    }
    return null;
  }
}
