<template>
  <div>
    <v-container>
      <v-form ref="form" lazy-validation v-if="loading">
        <v-row>
          <v-col>
            <v-text-field
              label="Name Tournament"
              v-model="nameTour"
              :rules="[(v) => !!v || 'Item is required']"
              required
            ></v-text-field>

            <v-menu
              ref="menu1"
              v-model="menu1"
              :close-on-content-click="false"
              :return-value.sync="menu1"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="timeStart"
                  label="Time start"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  :rules="rulesTimeStart"
                  required
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker v-model="timeStart" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu1 = false">
                  Cancel
                </v-btn>
                <v-btn
                  text
                  color="primary"
                  @click="$refs.menu1.save(timeStart)"
                >
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>

          <v-col>
            <v-text-field disabled v-model="type" label="Type"></v-text-field>
            <v-menu
              ref="menu2"
              v-model="menu2"
              :close-on-content-click="false"
              :return-value.sync="menu2"
              transition="scale-transition"
              offset-y
              min-width="290px"
            >
              <template v-slot:activator="{ on, attrs }">
                <v-text-field
                  v-model="timeEnd"
                  label="Time End"
                  prepend-icon="mdi-calendar"
                  readonly
                  v-bind="attrs"
                  v-on="on"
                  :rules="rulesTimeEnd"
                ></v-text-field>
              </template>
              <v-date-picker v-model="timeEnd" no-title scrollable>
                <v-spacer></v-spacer>
                <v-btn text color="primary" @click="menu2 = false">
                  Cancel
                </v-btn>
                <v-btn text color="primary" @click="$refs.menu2.save(menu2)">
                  OK
                </v-btn>
              </v-date-picker>
            </v-menu>
          </v-col>
        </v-row>
        <v-textarea
          label="Title"
          v-model="title"
          :rules="[(v) => !!v || 'Item is required']"
          required
        ></v-textarea>

        <v-row align="center" justify="space-around">
          <v-btn tile color="primary" @click.stop="dialog = true">
            <v-icon left> mdi-cancel </v-icon>
            Cancel
          </v-btn>
          <v-btn tile color="success" @click.prevent="submit">
            <v-icon left> mdi-pencil </v-icon>
            Submit
          </v-btn>
          
        </v-row>
      </v-form>
     <v-dialog v-model="dialog" max-width="290">
            <v-card>
              <v-card-title class="headline"> Notification </v-card-title>

              <v-card-text>
                Do you want to exit without finishing importing?
              </v-card-text>

              <v-card-actions>
                <v-spacer></v-spacer>

                <v-btn color="green darken-1" text @click="dialog = false">
                  Disagree
                </v-btn>

                <v-btn color="green darken-1" text @click="okCancel">
                  Agree
                </v-btn>
              </v-card-actions>
            </v-card>
          </v-dialog>
      <v-progress-circular
        :size="50"
        color="primary"
        indeterminate
        v-if="!loading"
      ></v-progress-circular>
    </v-container>
  </div>
</template>
<script>
import Tournament from "../../../models/tournament";
var dateNow = new Date().toISOString().substr(0, 10);
export default {
  props: {
    callback: {
      type: Function,
    },
    getList: {
      type: Function,
    },
    editData: {
      type: Object,
    },
  },
  data: () => ({
    Tournament: new Tournament(),
    timeStart: new Date().toISOString().substr(0, 10),
    timeEnd: new Date().toISOString().substr(0, 10),
    menu1: false,
    menu2: false,
    title: "",
    nameTour: "",
    rulesTimeStart: [(v) => dateNow <= v || "bigger date now"],
    rulesTimeEnd: [],
    type: "",
    loading: true,
    dialog:false
  }),
  methods: {
    clear() {
      (this.value = []),
        (this.nameTour = ""),
        (this.title = ""),
        (this.type = ""),
        (this.timeEnd = new Date().toISOString().substr(0, 10)),
        (this.timeStart = new Date().toISOString().substr(0, 10));
    },
    okCancel() {
      this.dialog=false
      this.clear();
      this.$refs.form.resetValidation();
      this.callback();
    },
    submit() {
      this.loading = false;
      this.Tournament.idTour = this.editData.idTour;
      this.Tournament.nameTour = this.nameTour;
      this.Tournament.timeEnd = this.timeEnd;
      this.Tournament.timeStart = this.timeStart;
      this.Tournament.type = this.type;
      this.Tournament.title = this.title;
      if (this.$refs.form.validate() == true) {
        this.$store
          .dispatch("tournament/edit", this.Tournament)
          .then((response) => {
            if (response.data == "edit") {
              this.getList();
              this.callback();
              alert("edit success");
            } else {
              alert(response.data);
            }
            this.loading = true;
          });
      } else {
        this.loading = true;
      }
    },
  },
  created() {
    this.nameTour = this.editData.nameTour;
    this.type = this.editData.type;
    this.timeStart = this.editData.timeStart;
    this.timeEnd = this.editData.timeEnd;
    this.title = this.editData.title;
    this.type = this.editData.type;
  },
  watch: {
    timeStart() {
      this.rulesTimeEnd = [(v) => v > this.timeStart || "bigger time start"];
    },
  },
};
</script>