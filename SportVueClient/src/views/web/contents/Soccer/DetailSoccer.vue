<template>
  <div style="background-color: white; width: 600px">
    <div>
      <v-icon v-if="this.type == 'Football'" large color="green darken-2">
        mdi-soccer
      </v-icon>
      <v-icon v-if="this.type == 'TableTennis'" large color="blue darken-2">
        sports_tennis
      </v-icon>
       <v-icon v-if="this.type == 'Basketball'" large color="blue darken-2">
        sports_basketball
      </v-icon>

      {{ data.nameTour }}
      <span
        style="
          float: right;
          font-size: 12px;
          margin-top: 10px;
          margin-right: 40px;
        "
        >{{ data.timeStart }}
        <v-icon small color="green darken-2">mdi-arrow-right </v-icon
        >{{ data.timeEnd }}
      </span>
    </div>
    <div>
      <v-row>
        <v-col class="text-center"
          ><b-img
            center
            :src="!!data ? data.team[0].logo : ''"
            style="width: 100px"
            alt="Center image"
          ></b-img>
          <div>{{ !!data ? data.team[0].nameTeam : "" }}</div>
        </v-col>
        <v-col class="text-center" style="margin-top: 10px"
          ><div v-if="data.status == 0">
            <h1>?-?</h1>
            Sắp diễn ra
          </div>
          <div v-if="data.status == 1">
            <h1>?-?</h1>
            Đang diễn ra
          </div>
          <div v-if="data.status == 2">
            <h1>{{ data.scoreTeam1 }} - {{ data.scoreTeam2 }}</h1>
            Kết thúc
          </div></v-col
        >
        <v-col class="text-center"
          ><b-img
            center
            :src="!!data ? data.team[1].logo : ''"
            style="width: 100px"
            alt="Center image"
          ></b-img>
          <div class="text-center">
            {{ !!data ? data.team[1].nameTeam : "" }}
          </div>
        </v-col>
      </v-row>
    </div>
    <div>
      <v-card>
        <v-tabs v-model="tab" dark flat>
          <v-tabs-slider></v-tabs-slider>

          <v-tab href="#tab-1"> Trận đấu </v-tab>

          <v-tab href="#tab-2"> Các trận đấu liên quan </v-tab>

          <v-tab href="#tab-3"> Hình ảnh </v-tab>
          <v-tab href="#tab-4"> Video </v-tab>
        </v-tabs>

        <v-tabs-items v-model="tab">
          <v-tab-item :value="'tab-' + 1">
            <v-card flat>
              <v-card-text>
                <v-card>
                  <v-tabs v-model="tabb">
                    <v-tabs-slider></v-tabs-slider>

                    <v-tab href="#tabb-1"> Thông tin trận đấu </v-tab>

                    <v-tab href="#tabb-2"> Thống kê cầu thủ </v-tab>

                    <v-tab href="#tabb-3"> Lịch sử trận đấu </v-tab>
                  </v-tabs>

                  <v-tabs-items v-model="tabb">
                    <!-- Thông tin trận đấu -->
                    <v-tab-item :value="'tabb-1'">
                      <v-card flat>
                        <v-card-text>
                          <div
                            class="text-center"
                            style="background-color: #bcaaa4"
                          >
                            Tỉ số
                          </div>
                          <table class="table">
                            <tbody>
                              <tr
                                v-for="(item, index) in data.round"
                                :key="index"
                              >
                                <td>
                                  {{ item.roundName }}
                                </td>
                                <td>
                                  {{ item.roundScore1 }}
                                </td>
                                <td>
                                  {{ item.roundScore2 }}
                                </td>
                              </tr>
                            </tbody>
                          </table>

                          <div
                            class="text-center"
                            style="background-color: #bcaaa4"
                          >
                            Thông tin trận đấu
                          </div>
                          Địa điểm:{{ data.address }}
                          <div
                            class="text-center"
                            style="background-color: #bcaaa4"
                          >
                            Thống kê chỉ số
                          </div>
                          <v-row>
                            <v-col cols="12" sm="4">
                              <br />
                              <ul>
                                <li>Tỉ lệ thắng</li>
                                <li>Trận trong giải</li>
                                <li>Trận thắng trong giải</li>
                                <li>Trận tham gia</li>
                                <li>Trận thắng</li>
                              </ul>
                            </v-col>
                            <v-col cols="12" sm="4">
                              <ul style="list-style: none">
                                <b>{{ !!data ? data.team[0].nameTeam : "" }}</b>
                                <li>{{ inforTeam1.rate }}%</li>
                                <li>{{ inforTeam1.sumJoinByTour }}</li>
                                <li>{{ inforTeam1.sumWinJoinByTour }}</li>
                                <li>{{ inforTeam1.sum }}</li>
                                <li>{{ inforTeam1.sumWin }}</li>
                              </ul>
                            </v-col>
                            <v-col cols="12" sm="4">
                              <ul style="list-style: none">
                                <b>{{ !!data ? data.team[1].nameTeam : "" }}</b>
                                <li>{{ inforTeam2.rate }}%</li>
                                <li>{{ inforTeam2.sumJoinByTour }}</li>
                                <li>{{ inforTeam2.sumWinJoinByTour }}</li>
                                <li>{{ inforTeam2.sum }}</li>
                                <li>{{ inforTeam2.sumWin }}</li>
                              </ul>
                            </v-col>
                          </v-row>
                          <div
                            class="text-center"
                            style="background-color: #bcaaa4"
                          >
                            Mô tả
                          </div>
                          <v-textarea
                            name="input-7-1"
                            :value="data.description"
                          ></v-textarea>
                          <div
                            class="text-center"
                            style="background-color: #bcaaa4"
                          >
                            Bình luận
                          </div>
                          <v-textarea
                            v-model="textComment"
                            style="margin-top: 20px"
                            solo
                            name="input-7-4"
                            height="30px"
                            placeholder="comment"
                          ></v-textarea>
                          <v-btn
                            depressed
                            color="success"
                            @click="commentSubmit"
                          >
                            Bình luận
                          </v-btn>
                          <v-card>
                            <v-card-text>
                              <div v-for="(item, i) in comment" :key="i">
                                <template v-if="i < number">
                                  <p>{{ item.nameProfile }}</p>
                                  <li>{{ item.text }}</li>
                                </template>
                              </div>
                              <div
                                class="text-center"
                                v-if="comment.length > 5"
                              >
                                <a style="color: blue">Hiển thị thêm</a>
                              </div>
                            </v-card-text>
                          </v-card>
                        </v-card-text>
                      </v-card>
                    </v-tab-item>
                    <!-- Thống kê cầu thủ -->
                    <v-tab-item :value="'tabb-2'">
                      <v-card flat>
                        <v-card-text>
                          <v-simple-table>
                            <template v-slot:default>
                              <thead>
                                <tr>
                                  <th class="text-left">Ảnh đại diện</th>
                                  <th class="text-left">Tên</th>
                                  <th class="text-left">Đội tuyển</th>
                                  <th class="text-left">Địa chỉ</th>
                                </tr>
                              </thead>
                              <tbody>
                                <tr
                                  v-for="(item, index) in team1.profile"
                                  :key="index"
                                >
                                  <td>
                                    <v-avatar>
                                      <img :src="item.avatar" alt="John" />
                                    </v-avatar>
                                  </td>
                                  <td>{{ item.name }}</td>
                                  <td>{{ team1.nameTeam }}</td>
                                  <td>{{ item.address }}</td>
                                </tr>
                                <tr
                                  v-for="(item, index) in team2.profile"
                                  :key="index"
                                >
                                  <td>
                                    <v-avatar>
                                      <img :src="item.avatar" alt="John" />
                                    </v-avatar>
                                  </td>
                                  <td>{{ item.name }}</td>
                                  <td>{{ team2.nameTeam }}</td>
                                  <td>{{ item.address }}</td>
                                </tr>
                              </tbody>
                            </template>
                          </v-simple-table>
                        </v-card-text>
                      </v-card>
                    </v-tab-item>
                    <v-tab-item :value="'tabb-3'">
                      <v-card>
                        <v-card-text>
                          <div>
                            <b-card no-body>
                              <b-tabs>
                                <b-tab
                                  :title="item.roundName"
                                  v-for="(item, index) in data.round"
                                  v-bind:key="index"
                                >
                                  <b-card-text>
                                    <h3>Tỉ số :</h3>
                                    <b-row>
                                      <b-col class="text-center"
                                        ><b-avatar
                                          :src="team1.logo"
                                          size="6rem"
                                        ></b-avatar
                                      ></b-col>
                                      <b-col class="text-center"
                                        ><h1>
                                          {{ item.roundScore1 }}-{{
                                            item.roundScore2
                                          }}
                                        </h1></b-col
                                      >
                                      <b-col class="text-center"
                                        ><b-avatar
                                          :src="team1.logo"
                                          size="6rem"
                                        ></b-avatar
                                      ></b-col>
                                    </b-row>
                                    Diễn biến :
                                    <b-form-textarea
                                      id="textarea-no-resize"
                                      placeholder="Description"
                                      rows="5"
                                      no-resize
                                      disabled
                                      :value="item.roundDescription"
                                    ></b-form-textarea>
                                  </b-card-text>
                                </b-tab>
                              </b-tabs>
                            </b-card>
                          </div>
                        </v-card-text>
                      </v-card>
                    </v-tab-item>
                  </v-tabs-items>
                </v-card>
              </v-card-text>
            </v-card>
          </v-tab-item>
          <v-tab-item :value="'tab-' + 2">
            <v-card flat>
              <v-card-text>
                <v-list-item-group
                  v-for="(item, index) in related"
                  color="primary"
                  :key="index"
                >
                  <v-list-item>
                    <v-list-item-content>
                      <v-list-item-title>
                        <v-row
                          v-b-popover.hover.bottom="'Kích vào để xem chi tiết'"
                        >
                          <v-col cols="12" sm="4">{{
                            item.status == 0
                              ? "Sắp diễn ra"
                              : item.status == 1
                              ? "Đang diễn ra"
                              : "Kết thúc"
                          }}</v-col>
                          <v-col>
                            <v-row>
                              <v-col cols="12" sm="4">
                                {{ !!data ? item.team[0].nameTeam : "" }}
                              </v-col>
                              <v-col cols="12" sm="4"
                                >{{ item.status == 2 ? item.scoreTeam1 : "?" }}
                                -
                                {{ item.status == 2 ? item.scoreTeam2 : "?" }}
                              </v-col>
                              <v-col cols="12" sm="4">
                                {{ !!data ? item.team[1].nameTeam : "" }}</v-col
                              >
                            </v-row>
                          </v-col>
                        </v-row>
                      </v-list-item-title>
                    </v-list-item-content>
                  </v-list-item>
                </v-list-item-group>
              </v-card-text>
            </v-card>
          </v-tab-item>
          <v-tab-item :value="'tab-' + 3">
            <v-card flat>
              <v-card-text class="text-center">
                <v-img
                  lazy-src="data:image/jpeg;base64,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"
                  max-height="150"
                  max-width="250"
                  :src="data.image"
                ></v-img>
              </v-card-text>
            </v-card>
          </v-tab-item>
          <v-tab-item :value="'tab-' + 4">
            <v-card flat>
              <v-card-text>
                <video controls :src="data.video" width="550px"></video>
              </v-card-text>
            </v-card>
          </v-tab-item>
        </v-tabs-items>
      </v-card>
    </div>
  </div>
</template>
<script>
export default {
  data() {
    return {
      tab: null,
      data: "",
      tabb: null,
      inforTeam1: "",
      inforTeam2: "",
      team1: "",
      team2: "",
      related: "",
      type: "",
      number: 5,
      textComment: "",
      comment: [],
    };
  },
  created() {
    this.$store
      .dispatch("schedule/getById", this.$route.params.id)
      .then((response) => {
        this.data = response.data;
        this.type = response.data.team[0].type;
        this.$store
          .dispatch("team/getDetail", {
            params: {
              idTeam: response.data.team[0].idTeam,
              idTour: response.data.idTour,
            },
          })
          .then((response) => {
            this.inforTeam1 = response.data;
          });
        this.$store
          .dispatch("team/teamTourHistory", {
            idTour: response.data.idTour,
            idTeam: response.data.idTeam1,
          })
          .then((response) => {
            this.team1 = response.data;
          });
        this.$store
          .dispatch("team/teamTourHistory", {
            idTour: response.data.idTour,
            idTeam: response.data.idTeam2,
          })
          .then((response) => {
            this.team2 = response.data;
          });
        this.$store
          .dispatch("team/getDetail", {
            params: {
              idTeam: response.data.team[1].idTeam,
              idTour: response.data.idTour,
            },
          })
          .then((response) => {
            this.inforTeam2 = response.data;
          });

        this.$store
          .dispatch("schedule/related", {
            idTour: response.data.idTour,
            idTeam1: response.data.idTeam1,
            idTeam2: response.data.idTeam2,
          })
          .then((response) => {
            this.related = response.data;
          });
        this.$store
          .dispatch("comment/getAll", this.$route.params.id)
          .then((response) => {
            this.comment = response.data;
          });
      });
  },
  methods: {
    getComment() {
      this.$store
        .dispatch("comment/getAll", this.$route.params.id)
        .then((response) => {
          this.comment = response.data;
        });
    },
    commentSubmit() {
    
      if (this.$store.state.user.userInfo == null) {
        alert("cần đăng nhập");
      } else {
        if (this.textComment != "") {
          this.$store
            .dispatch("comment/addComment", {
              text: this.textComment,
              idSchedule: this.data.idSchedule,
              nameProfile: this.$store.state.user.userInfo.profile.name,
            })
            .then((response) => {
              this.getComment();
              this.textComment=''
            });
        }
      }
    },
  },
};
</script>